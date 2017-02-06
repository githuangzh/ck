package com.will.bussiness.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.will.bussiness.common.Constant;
import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IProfileService;
import com.will.bussiness.system.service.IRoleService;
import com.will.utility.Pagination;
import com.will.utility.Result;

/**
 * 角色层
 * @author huangzh
 *
 */
@Controller
@RequestMapping("role")
public class RoleController {
	
	@Resource
	private IRoleService rolesrv;
	
	@RequestMapping("/index.html")
	public String index(){
		return "system/role/role_index";
	}
	
	@RequestMapping("/roleauth.html")
	public String roleauth(HttpServletRequest request,SysRole sysRole){
		rolesrv.findRoleWithUserOrResource(sysRole, 1);
		return "system/role/role_auth";
	}
	
	@RequestMapping("/list.json")
	@ResponseBody
	public Map<String, Object> getAllRoles(HttpServletRequest request,Pagination<SysRole> pagination,
			SysRole role,ModelMap modelMap){
		try {
			if(StringUtils.hasText(role.getRoleid())){
				role.setRoleid(URLDecoder.decode(role.getRoleid(),"UTF-8"));
			}
			if(StringUtils.hasText(role.getRolename())){
				role.setRolename(URLDecoder.decode(role.getRolename(),"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		pagination = rolesrv.findSysRoleByPage(pagination, role);
		HashMap<String, Object> page = new HashMap<String, Object>();
		page.put("total", pagination.getTotalRows());
		page.put("rows", pagination.getList());
		return page;
	}
	
	
}

