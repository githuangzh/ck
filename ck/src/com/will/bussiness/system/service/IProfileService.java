package com.will.bussiness.system.service;

import java.util.List;

import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysUser;
import com.will.utility.Result;

public interface IProfileService {

	public Result auth(SysUser user);
	
	public List<SysRole> findRoleByUserid(SysUser user);
}
