package com.will.bussiness.system.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.filenet.api.core.Factory.User;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.will.bussiness.common.Constant;
import com.will.bussiness.system.dao.ISysRoleDao;
import com.will.bussiness.system.dao.ISysStaffDao;
import com.will.bussiness.system.dao.ISysUserDao;
import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysStaff;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IRoleService;
import com.will.bussiness.system.service.IUserService;
import com.will.exception.ResultException;
import com.will.utility.MD5String;
import com.will.utility.Pagination;
import com.will.utility.Result;

@Service
public class RoleServiceImpl implements IRoleService {
	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	@Resource
	private ISysRoleDao roleDao ;
	public Pagination<SysRole> findSysRoleByPage(
			Pagination<SysRole> pagination, SysRole sysRole) {
		pagination.setList(roleDao.findSysRoleByPage(pagination, sysRole));
		return pagination;
	}
	private Result checkSysRole(SysRole sysRole) {
		HashMap<String, String> message = new HashMap<String, String>();
		Result result = new Result("添加成功");
		//判断编号
		SysRole role =roleDao.findSysRoleByRoleid(sysRole);
		if(role != null){
			result = new Result(Result.FAILURE, 40101, message.put("roleid", "角色编号已存在"));
		}
		return result;
	}
	public Result addSysRole(SysRole sysRole) {
		int dbid = sysRole.getDbid();
		if(dbid == 0){
			//判断角色是否有重复性
			Result result = new Result("添加成功");
			result = checkSysRole(sysRole);
			//新增角色
			if(result.isSucc()){
				try {
					roleDao.addRole(sysRole);
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
					e.printStackTrace();
				}
			}
			return result;
		}else{
			Result result = new Result("修改成功");
			//判断角色是否有重复性
			SysRole role = roleDao.findSysRoleByDbid(sysRole);
			if(sysRole.equals(role)){
				result.setStatusCode(300);
				return result;
			}
			//修改角色
			result = checkSysRole(sysRole);
			if(result.isSucc()){
				try {
					roleDao.updateRole(sysRole);
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
					e.printStackTrace();
				}
			}
			return result;
		}
	}
	
	public Result removeSysRole(SysRole sysRole) {
		Result result = new Result("删除成功");
		//判断角色下是否有用户。
		List<SysStaff> staffs = roleDao.findRoleWithUser(sysRole);
		if(staffs.size() > 0){
			result = new Result(Result.FAILURE,Constant.RESULT_FAILD,"请先移除角色下用户");
		}else{
			//删除资源与角色关联表
			try {
				roleDao.removeRoleWithResource(sysRole);
			} catch (ResultException e) {
				logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
				e.printStackTrace();
			}
			//删除资源
			try {
				roleDao.removeRole(sysRole);
			} catch (ResultException e) {
				logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
				e.printStackTrace();
			}
		}
		return result;
	}
	public Result createRoleWithUser(SysRole sysRole,String[] userIds) {
		Result result = new Result("添加成功");
		//先删除角色下用户
		try {
			roleDao.removeRoleWithUser(sysRole);
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
			e.printStackTrace();
		}
		//添加角色下用户根据userid
		try {
			roleDao.addRoleWithUserByArray(userIds,sysRole);
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
			e.printStackTrace();
		}
		return result;
	}
	public Result createRoleWithResource(SysRole sysRole,String[] resIds) {
		Result result = new Result("添加成功");
		//先删除角色下资源
		try {
			roleDao.removeRoleWithResource(sysRole);
			//添加角色下资源
			roleDao.addRoleWithResourceByArray(resIds,sysRole);
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========请求资源失败,数据或数据库异常");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<?> findRoleWithUserOrResource(SysRole sysRole,Integer optId){
		//根据optid查询 0：查询用户下已有用户 1:查询用户下已有资源
		if(optId == 0){
			//查询用户
			List<SysStaff> list = roleDao.findRoleWithUser(sysRole);
			return list;
		}else{
			//查询资源
			List<SysResource> list = roleDao.findRoleWithResurce(sysRole);
			return list;
		}
	}
	
}
