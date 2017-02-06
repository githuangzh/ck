package com.will.bussiness.system.service;

import org.springframework.stereotype.Service;

import com.will.bussiness.system.entity.SysUser;
import com.will.utility.Pagination;
import com.will.utility.Result;

public interface IUserService {
	/**
	 * 
	 * @param pagination
	 * @param user
	 * @return
	 */
	public Pagination<SysUser> findSysUserByPage(Pagination<SysUser> pagination,SysUser user);

	/**
	 * 修改添加用户
	 * @param user
	 * @return
	 */
	public Result addSysUser(SysUser user);
	/**
	 * 查询用户--根据dbid
	 * @param user
	 * @return
	 */
	public SysUser findUserByDbid(SysUser user);
	
	
	public Result  updatePassword(SysUser user,String newpwd);
	
	
}