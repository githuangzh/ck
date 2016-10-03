package com.will.plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.will.init.SystemPropertiesLoader;
import com.will.utility.Pagination;

/**
 * MyBatis分页组件
 * @author HuangZh
 *
 */
@Intercepts(@Signature(type=StatementHandler.class,method="prepare",args={Connection.class}))
public class PagePlugin implements Interceptor {
	private static final Log logger = LogFactory.getLog(PagePlugin.class);
	private static String defaultDialect = "oracle";
	private static String defaultPageSqlId = ".*Page$";
	private static ObjectFactory objectFactory = new DefaultObjectFactory();
	private static ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory(); 
	private static String dialect = ""; // 数据库类型(默认为mysql)
    private static String pageSqlId = ""; // 需要拦截的ID(正则匹配)
	public Object intercept(Invocation inv) throws Throwable {
		StatementHandler statementHandler = null;
		if(inv.getTarget() instanceof StatementHandler){
			statementHandler = (StatementHandler) inv.getTarget();
		}
		if(null == statementHandler){
			return inv.proceed();
		}
		//MetaObject更方便的获取statementHandler的属性支持OGNL表达式,这样不要通过复杂的反射方式来实现
		MetaObject metaStatementHandler =MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory);
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object obj = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(obj, objectFactory, objectWrapperFactory);
		}
		while (metaStatementHandler.hasGetter("target")){
			Object obj = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(obj, objectFactory, objectWrapperFactory);
		}
		//获取配置文件内容
		//获取配置文件dialect属性   数据库类型
		dialect = SystemPropertiesLoader.pro.getProperty("plugin.dialect");
		//为空使用默认数据库类型
		if(null == dialect || "".equals(dialect)){
			logger.warn("Property dialect is not setted,use default 'oracle' ");
			dialect = defaultDialect;
		}
		//获取配置文件pageSqlId属性，用来过滤什么样的mapper重写sql
		pageSqlId= SystemPropertiesLoader.pro.getProperty("plugin.pageSqlId");
		//为空使用默认参数
		if(null == pageSqlId || "".equals(pageSqlId)){
			logger.warn("Property pageSqlId is not setted,use default 'page' ");
			pageSqlId = defaultPageSqlId;
		}
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		if(mappedStatement.getId().matches(pageSqlId)){
			//获得Mapper中编写的sql语句
			
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			//获取Mybatis的sql参数
			Object obj = boundSql.getParameterObject();
			//参数为空报sql异常
			if(null == obj){
				throw new NullPointerException("Sql Parameter is NULL!");
			}else{
				Pagination pagination = (Pagination) metaStatementHandler.getValue("delegate.boundSql.parameterObject.page");
				//获得sql语句
				String  sql = boundSql.getSql();
				//TODO 重写SQL语句
				String pageSql = buildPageSql(sql,pagination);
				metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
                Connection connection  = (Connection) inv.getArgs()[0];
                setPageParameter(sql,connection,mappedStatement, boundSql, pagination);
                //setPageList(boundSql,connection,mappedStatement,pagination);
			}
		}
		//放行
		return inv.proceed();
	}
	

/*	private void setPageList(BoundSql boundSql, Connection connection,
			MappedStatement mappedStatement, Pagination pagination) {
		String sql = boundSql.getSql();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	private void setPageParameter(String sql, Connection connection,
			MappedStatement mappedStatement, BoundSql boundSql,
			Pagination pagination) {
		System.out.println(sql);
		String countSql = "select count(0) count from ("+sql+") total";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(countSql);
			setParameters(preparedStatement, mappedStatement, boundSql, boundSql.getParameterObject());
			resultSet =preparedStatement.executeQuery();
			int totalCount = 0;
			while (resultSet.next()) {
				totalCount = resultSet.getInt("count");
			}
			pagination.setTotalRows(totalCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
     * 对SQL参数(?)设值
     * 
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

	/**
     * 根据数据库类型，生成特定的分页sql
     * 
     * @param sql
     * @param pagination
     * @return
     */
    private String buildPageSql(String sql, Pagination pagination) {
        if (pagination != null) {
            StringBuilder pageSql = new StringBuilder();
            if ("mysql".equals(dialect)) {
                pageSql = buildPageSqlForMysql(sql, pagination);
            } else if ("oracle".equals(dialect)) {
                pageSql = buildPageSqlForOracle(sql, pagination);
            } else {
                return sql;
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    /**
     * mysql的分页语句
     * 
     * @param sql
     * @param pagination
     * @return String
     */
    public StringBuilder buildPageSqlForMysql(String sql, Pagination pagination) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf(pagination.getPageIndex());
        pageSql.append(sql);
        pageSql.append(" limit " + beginrow + "," + pagination.getPageSize());
        return pageSql;
    }
	
	/**
     *实现完成oracle的分页
     * 
     * @param sql
     * @param pagination
     * @return String
     */
    public StringBuilder buildPageSqlForOracle(String sql, Pagination pagination) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf((pagination.getPageIndex() - 1) * pagination.getPageSize());
        String endrow = String.valueOf(pagination.getPageIndex() * pagination.getPageSize());
        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(" ) temp where rownum <= ").append(endrow);
        pageSql.append(") where row_id > ").append(beginrow);
        return pageSql;
    }
	
	public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
	
	public void setProperties(Properties properties) {
		
	}

}
