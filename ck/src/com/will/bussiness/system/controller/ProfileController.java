package com.will.bussiness.system.controller;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.will.bussiness.common.Constant;
import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IProfileService;
import com.will.utility.Result;

/**
 * 登陆层
 * @author huangzh
 *
 */
@Controller
@RequestMapping("profile")
public class ProfileController {
	
	@Resource
	private IProfileService profilesrv;
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpSession session){
		return "system/login";
	}
	
	
	@RequestMapping("auth")
	@ResponseBody
	public Result auth(SysUser user,HttpServletRequest request,ModelMap model){
		HttpSession session = request.getSession();
		Result result = profilesrv.auth(user);
		if(result.isSucc()){
			session.setAttribute(Constant.SESSION_OBJECT, result.getData());
			initLoginInfo(model,request,(SysUser)result.getData());
		}
		return result;
	}
	
	@RequestMapping("admin")
	public String admin(HttpServletRequest request){
		return "system/admin";
	}
	
	@RequestMapping("exit")
	String exitSys(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Constant.SESSION_OBJECT);
		if (null != obj) {
			request.getSession().setAttribute(Constant.SESSION_OBJECT, null);
			request.getSession().setMaxInactiveInterval(0);
			request.getSession().invalidate();
		}
		return "redirect:/";
	}
	/**
	 * 初始化登录人员会话信息
	 * 
	 * @param model
	 * @param request
	 * @param user
	 */
	private void initLoginInfo(ModelMap model, HttpServletRequest request,
			SysUser user) {
		request.getSession().setAttribute(Constant.SESSION_OBJECT, user);
		// 用户角色
		// 读取角色
		List<SysRole> roles = profilesrv.findRoleByUserid(user);
		request.getSession().setAttribute(Constant.SESSION_ROLOES, roles);
		//资源列表
		request.getSession().setAttribute("rlist", user.getResource());
		for (SysRole sysRole : roles) {
			if(sysRole.getRoleid().equals(Constant.ROLE_ADMIN)){
				request.getSession().setAttribute("isadmin", "1");
			}else{
				request.getSession().setAttribute("isadmin", "0");
			}
			
			if(sysRole.getRoleid().equals(Constant.ROLE_BOSS)){
				request.getSession().setAttribute("isboss", "1");
			}else{
				request.getSession().setAttribute("isboss", "0");
			}
			
			if(sysRole.getRoleid().equals(Constant.ROLE_MANAGER)){
				request.getSession().setAttribute("ismanager", "1");
			}else{
				request.getSession().setAttribute("ismanager", "0");
			}
			
			if(sysRole.getRoleid().equals(Constant.ROLE_EMPLOYEE)){
				request.getSession().setAttribute("isempolyee", "1");
			}else{
				request.getSession().setAttribute("isempolyee", "0");
			}
		}
		
	}
}
