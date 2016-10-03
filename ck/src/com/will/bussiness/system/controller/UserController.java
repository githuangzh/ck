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

import com.will.bussiness.system.entity.SysStaff;
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
	public Map<String, Object> getAllUser(HttpServletRequest request, SysUser user,
			ModelMap model,Pagination pagination,SysStaff staff) {
		//String userid = request.getParameter("userid");
		//String username = request.getParameter("username");
		try {
			if(StringUtils.hasText(staff.getUserid())){
				staff.setUserid(URLDecoder.decode(staff.getUserid(),"UTF-8"));
			}
			if(staff.getUser() != null && StringUtils.hasText(staff.getUser().getUsername())){
				staff.getUser().setUsername(URLDecoder.decode(staff.getUser().getUsername(),"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		pagination = usersrv.findSysStaffByPage(pagination,staff);
		HashMap<String, Object> page = new HashMap<String, Object>();
		page.put("total", pagination.getTotalRows());
		page.put("rows", pagination.getList());
		return page;
	}
	
	
	@RequestMapping("/init.html")
	public String addUserview(HttpServletRequest request,ModelMap model){
		String dbid = request.getParameter("dbid");
		if(StringUtils.hasText(dbid) && "0".equals(dbid)){
			SysStaff staff = new SysStaff();
			SysUser user = new SysUser();
			staff.setUser(user);
			model.addAttribute("data", staff);
		}else{
			SysStaff staff = new SysStaff();
			staff.setDbid(Integer.valueOf(dbid));
			staff = usersrv.findSysStaffByDbid(staff);
			model.addAttribute("data", staff);
		}
		return "system/user_add";
	}
	
	@RequestMapping("/add.json")
	@ResponseBody
	public Result addUser(HttpServletRequest request,SysStaff sysStaff){
		return usersrv.addSysStaff(sysStaff);
	}
	
	@RequestMapping("/remove.json")
	@ResponseBody
	public Result removeUser(HttpServletRequest request,SysStaff sysStaff){
		SysStaff staff = usersrv.findSysStaffByDbid(sysStaff);
		return usersrv.RemoveSysStaff(staff);
	}
	
	@RequestMapping("/updatepay.html")
	public String updatePayView(HttpServletRequest request,ModelMap model){
		String dbid = request.getParameter("dbid");
		SysStaff staff = new SysStaff();
		staff.setDbid(Integer.valueOf(dbid));
		staff = usersrv.findSysStaffByDbid(staff);
		model.addAttribute("data", staff);
		return "system/user_pay";
	}
	@RequestMapping("/updatepay.json")
	@ResponseBody
	public Result updatePay(HttpServletRequest request,SysStaff sysStaff){
		return usersrv.UpdateSysStaffPay(sysStaff);
		//return new Result();
	}
	
	
}
