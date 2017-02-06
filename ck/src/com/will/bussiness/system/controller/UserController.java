package com.will.bussiness.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IUserService;
import com.will.utility.Pagination;
import com.will.utility.Result;


/**
 * 账户管理
 * @author huangzh
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private IUserService usersrv;
	
	@RequestMapping("/index.html")
	public String index(){
		return "system/user_index";
	}
	
	@RequestMapping("/list.json")
	@ResponseBody
	public Map<String, Object> getAllUser(HttpServletRequest request,
			ModelMap model,Pagination pagination,SysUser user) {
		//String userid = request.getParameter("userid");
		//String username = request.getParameter("username");
		try {
			if(StringUtils.hasText(user.getUserid())){
				user.setUserid(URLDecoder.decode(user.getUserid(),"UTF-8"));
			}
			if(StringUtils.hasText(user.getStaffid())){
				user.setStaffid(URLDecoder.decode(user.getStaffid(),"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		pagination = usersrv.findSysUserByPage(pagination,user);
		HashMap<String, Object> page = new HashMap<String, Object>();
		page.put("total", pagination.getTotalRows());
		page.put("rows", pagination.getList());
		return page;
	}
	
	
	@RequestMapping("/init.html")
	public String addUserview(HttpServletRequest request,ModelMap model){
		String dbid = request.getParameter("dbid");
		if(StringUtils.hasText(dbid) && "0".equals(dbid)){
			SysUser user = new SysUser();
			model.addAttribute("data", user);
		}else{
			SysUser user =new SysUser();
			user.setDbid(Integer.valueOf(dbid));
			user = usersrv.findUserByDbid(user);
			model.addAttribute("data", user);
		}
		return "system/user_add";
	}
	
	@RequestMapping("/add.json")
	@ResponseBody
	public Result addUser(HttpServletRequest request,SysUser user){
		return usersrv.addSysUser(user);
	}
	
	@RequestMapping("/status.json")
	@ResponseBody
	public Result updateUserByStatus(HttpServletRequest request,SysUser user){
		SysUser usr = usersrv.findUserByDbid(user);
		usr.setStatus(~usr.getStatus()+2);
		return usersrv.addSysUser(usr);
	}
	
	@RequestMapping("/updatepwd.html")
	public String updatePassword(SysUser user,ModelMap model){
		SysUser usr = usersrv.findUserByDbid(user);
		model.addAttribute("data", usr);
		return "system/user_pwd";
	}
	
	@RequestMapping("/updatepwd.json")
	@ResponseBody
	public Result updatePassword(SysUser user,String newpwd,HttpServletRequest request){
		return usersrv.updatePassword(user, newpwd);
	}
    //TODO -- 关联员工	
	
}
