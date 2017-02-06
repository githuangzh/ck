package com.will.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.will.bussiness.system.entity.SysUser;
import com.will.exception.ResultException;
import com.will.utility.Pagination;
import com.will.utility.SupperMapper;


@SupperMapper
public interface ISysUserDao {
	/**
	 * 查询用户
	 * @param userid
	 * @return
	 */
	public SysUser findByUserid(String userid);
	
	/**
	 * 查询用户
	 * @param dbid
	 * @return
	 */
	public SysUser findUserByDbid(int dbid);
	/**
	 * 查询用户--表格
	 * @param pagination
	 * @return
	 */
	public List<SysUser> findUserByPage(@Param("page")Pagination<SysUser> pagination,@Param("user")SysUser user);
	
	/**
	 * 添加账号
	 * @param sysuser
	 */
	public int addSysUser(SysUser sysuser) throws ResultException;
	
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 * @throws ResultException
	 */
	public int updateUserid(SysUser user) throws ResultException;
	
	/**
	 * 修改账号
	 * @param user
	 * @return
	 * @throws ResultException
	 */
	public int updateSysUser(SysUser user)  throws ResultException;
	
	/**
	 * 禁用账号
	 * @param user
	 * @return
	 * @throws ResultException
	 */
	public int updateStatus(SysUser user) throws ResultException;
	
}
