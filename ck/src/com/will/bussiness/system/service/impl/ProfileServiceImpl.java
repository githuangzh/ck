package com.will.bussiness.system.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.print.attribute.standard.Fidelity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.will.bussiness.system.dao.ISysResourceDao;
import com.will.bussiness.system.dao.ISysRoleDao;
import com.will.bussiness.system.dao.ISysUserDao;
import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IProfileService;
import com.will.utility.MD5String;
import com.will.utility.Pagination;
import com.will.utility.Result;

@Service
public class ProfileServiceImpl implements IProfileService {
	@Resource
	private ISysUserDao sysDao;
	@Resource
    private ISysResourceDao resDao;
	@Resource
	private ISysRoleDao roleDao;
	
	public Result auth(SysUser user) {
		Result result = new Result();
		user.setPassword(MD5String.getMD5Str(user.getPassword()));
		SysUser sysUser = sysDao.findByUserid(user.getUserid());
		if(sysUser == null){
			result = new Result(Result.FAILURE, "用户不存在");
			result.setStatusCode(101);
		}else if(sysUser.getPassword().equals(user.getPassword())){
			sysUser.setPassword(null);
			result = new Result(Result.SUCC,sysUser,"登录成功");
			//获取当前用户资源
			LinkedHashMap<String,ArrayList<SysResource>> resources = findSysResource(sysUser);
			sysUser.setResource(resources);
		}else{
			result = new Result(Result.FAILURE,"密码错误");
			result.setStatusCode(102);
		}
		return result;
	}
	
	public LinkedHashMap<String,ArrayList<SysResource>> findSysResource(SysUser user){
		LinkedHashMap<String,ArrayList<SysResource>> map = new LinkedHashMap<String, ArrayList<SysResource>>();
		
		List<SysResource> list = resDao.findSysResourceByUserId(user.getUserid());
		map.put("root",new ArrayList<SysResource>());
		for (SysResource sysResource : list) {
			if(StringUtils.hasText(sysResource.getParentid()) && !map.containsKey(sysResource.getParentid())){
				ArrayList<SysResource> resources = new ArrayList<SysResource>();
				resources.add(sysResource);
				map.put(sysResource.getParentid(), resources);
			}else{
				map.get(sysResource.getParentid()).add(sysResource);
			}
		}
		return map;
	}
	
	public List<SysRole> findRoleByUserid(SysUser user){
		return roleDao.findRolesByUserid(user.getUserid());
	}
	
}
