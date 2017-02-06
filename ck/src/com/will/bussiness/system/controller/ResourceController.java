package com.will.bussiness.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.servlet.ModelAndView;

import com.will.bussiness.common.Constant;
import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IProfileService;
import com.will.bussiness.system.service.IResourceService;
import com.will.utility.Pagination;
import com.will.utility.Result;

/**
 * 资源管理
 * @author huangzh
 *
 */
@Controller
@RequestMapping("resource")
public class ResourceController {
	@Resource
	private IResourceService ressrv;
	
	@RequestMapping("/index.html")
	public String index(){
		return "system/resource/res_index";
	}
	
	@RequestMapping("/list.json")
	@ResponseBody
	public Map<String, Object> getAllResource(HttpServletRequest request,SysResource resource,
			ModelMap model,Pagination pagination){
		try {
			if(StringUtils.hasText(resource.getResid())){
				resource.setResid(URLDecoder.decode(resource.getResid(),"UTF-8"));
			}
			if(StringUtils.hasText(resource.getResname())){
				resource.setResname(URLDecoder.decode(resource.getResname(),"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		pagination = ressrv.findSysResourceByPage(pagination, resource);
		HashMap<String, Object> page = new HashMap<String, Object>();
		page.put("total", pagination.getTotalRows());
		page.put("rows", pagination.getList());
		return page;
	}
	
	
	
	
	@RequestMapping("/init.html")
	public String addResource(HttpServletRequest request,ModelMap model){
		String dbid = request.getParameter("dbid");
		if(StringUtils.hasText(dbid) && "0".equals(dbid)){
			SysResource resource = new SysResource();
			model.addAttribute("data", resource);
		}else{
			SysResource res = new SysResource();
			res.setDbid(Integer.valueOf(dbid));
			res = ressrv.findSysResourceByDbid(res);
			model.addAttribute("data", res);
		}
		return "system/resource/res_add";
	}
	@RequestMapping("/topid.json")
	@ResponseBody
	public Result getResourceTopid(HttpServletRequest request,ModelMap model){
		List<String> list = ressrv.getResourceTopid();
		Result result = new Result();
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/add.json")
	@ResponseBody
	public Result addUser(HttpServletRequest request,SysResource resource){
		return ressrv.addSysResource(resource);
	}
	
	@RequestMapping("/remove.json")
	@ResponseBody
	public Result removeUser(HttpServletRequest request,SysResource resource){
		SysResource res = ressrv.findSysResourceByDbid(resource);
		//TODO write code 删除资源判断资源下是否有角色
		return ressrv.RemoveSysResource(res);
	}
}
