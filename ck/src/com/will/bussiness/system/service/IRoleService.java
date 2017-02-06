package com.will.bussiness.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;

import com.will.bussiness.system.entity.SysUser;
import com.will.utility.Pagination;
import com.will.utility.Result;


/**
 * @author Will
 *
 */
/**
 * @author Will
 *
 */
public interface IRoleService {

	
	/**
	 * 查询角色列表
	 * @return
	 */
	public Pagination<SysRole> findSysRoleByPage(Pagination<SysRole> pagination,SysRole sysRole);
	
	/**
	 * 创建角色
	 * @return
	 */
	public Result addSysRole(SysRole sysRole);
	
	/**
	 * 删除角色
	 * @return
	 */
	public Result removeSysRole(SysRole sysRole);
	
	/**
	 * 角色与用户管理
	 * @return
	 */
	public Result createRoleWithUser(SysRole sysRole,String [] userIds);
	
	/**
	 * 角色与资源管理
	 * @return
	 */
	public Result createRoleWithResource(SysRole sysRole,String [] resId);
	
	
	/**
	 * 查询角色下用户或者资源
	 * @param sysRole
	 * @param optId
	 * @return
	 */
	public List<?> findRoleWithUserOrResource(SysRole sysRole,Integer optId);
	
	
	
}
