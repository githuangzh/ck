package com.will.bussiness.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统常量
 * 
 * @author huangzh
 * 
 */
public interface Constant {
	/** 分页页面记录数（0042） */
	public static final int PAGE_SIZE = 10;
	/** 分页条显示导航条数量（0044） */
	public static final int NAVIGATE_PAGES = 6;
	/** 结果执行成功（0046） */
	public static final int RESULT_SUCC = 1;
	/** 结果执行失败（0048） */
	public static final int RESULT_FAILD = 400;
	/** 最大文件上传值 */
	public static final long UPLOAD_FILE_MAX_SIZE = 10485760;

	/** 管理员的管理权限 */
	public static final String MANAGE_DEPT = "MANAGE_DEPT";
	/** 密码更新成功（0052） */
	public static final String PWD_UPDATE_SUCC = "密码更新成功！";
	/** 密码更新失败 （0054） */
	public static final String PWD_UPDATE_FAILED = "密码更新失败！";
	/** 密码检验不通过(0056) */
	public static final String PWD_VERIFY_FAILED = "密码检验不通过！";
	/** 两次输入的密码不一致(0058) */
	public static final String PWD_ENDTERD_DIFFER = "两次输入的密码不一致！";
	/** 密码加密盐值 */
	public static final String ENCRYPT_SALT = "javacspring@gmail.com";
	/** 待办事宜模块 */
	public static final String SYS_MODUAL_TODO = "1";
	/** 用户管理 */
	public static final String SYS_MODUAL_USER_MGR = "2";
	/** 同步结果(失败) */
	public static final String SYNC_RESULT_FAIL = "0";
	/** 同步结果(成功) */
	public static final String SYNC_RESULT_SUCCESS = "1";
	/** 会话登录用户标识 */
	public static final String SESSION_OBJECT = "__SESSION_OBJECT";
	/** 角色 */
	public static final String SESSION_ROLOES = "__SESSION_ROLOES";
	/** 超时跳转页面 */
	public static final String PAGE_TIMIE_OUT = "/WEB-INF/page/system/timeout.jsp";
	public static final String LOG_RECORD = "__RECORDER_INFO";
	/** 系统名称 */
	public static final String SYS_NAME = "江西移动PORTAL平台";
	/** 系统名称 */
	public static final String YXT_NAME = "单点登录云学堂";
	/** 系统名称 */
	public static final String YXT_CZLJ = "学习成长路径图";
	/** 操作类型 */
	public static final int CREATE = 1;
	public static final int UPDATE = 2;
	public static final int READ = 3;
	public static final int DELETE = 4;
	public static final int DAOCHU = 5;
	public static final int QUERY = 6;
	
	
	public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMAT2DATE = "yyyy-MM-dd";
	
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_BOSS = "1001";
	public static final String ROLE_MANAGER = "1002";
	public static final String ROLE_EMPLOYEE = "1111";
	
	
	/**启用*/
	public static final int STATUS_ENABLED = 0;

	/**禁用*/
	public static final int STATUS_DISABLED = 1;

	
	
	
	
}
