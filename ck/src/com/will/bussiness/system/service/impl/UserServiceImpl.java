package com.will.bussiness.system.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.will.bussiness.ck.dao.IRlStaffDao;
import com.will.bussiness.common.Constant;
import com.will.bussiness.system.dao.ISysUserDao;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IUserService;
import com.will.exception.ResultException;
import com.will.utility.MD5String;
import com.will.utility.Pagination;
import com.will.utility.Result;

@Service
public class UserServiceImpl implements IUserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Resource
	private ISysUserDao userDao;

	public Pagination<SysUser> findSysUserByPage(
			Pagination<SysUser> pagination,SysUser user) {
		//List list = staffDao.findSysStaffByPage(pagination,sysStaff);
		pagination.setList(userDao.findUserByPage(pagination, user));
		return pagination;
	}
	
	/**
	 *  检查账号信息--用户名不可重复
	 * @return
	 */
	public Result checkSysUser(SysUser user,Result result){
		HashMap<String, String> message = new HashMap<String, String>();
		SysUser sysuser = null;
		if(null != user.getUserid()){
			sysuser = userDao.findByUserid(user.getUserid());
		}else{
			result = new Result(Result.FAILURE,"请输入用户名");
		}
		if(sysuser != null){
			message.put("userid", "用户名已存在");
			result = new Result(Result.FAILURE, 40102, message);
		}
		return result;
	}
	public Result addSysUser(SysUser user){
		int dbid = user.getDbid();
		if(dbid == 0 ){
			Result result = new Result("添加成功");
			result = checkSysUser(user,result);
			if(result.isSucc()){
				try {
					SysUser sysUser = new SysUser();
					sysUser.setUserid(user.getUserid());
					sysUser.setPassword(MD5String.getMD5Str("123456"));
					//初始新建用户为禁用模式
					sysUser.setStatus(Constant.STATUS_DISABLED);
					sysUser.setStaffid(user.getStaffid());
					userDao.addSysUser(sysUser);
					
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========添加员工失败,数据或数据库异常");
					e.printStackTrace();
				}
			}
			return result;
		}else{
			Result result = new Result("修改成功");
			SysUser usr = userDao.findUserByDbid(dbid);
			if(usr.equals(user)){
				result.setStatusCode(300);
				return result;
			}
			//修改用户
			if(!usr.getUserid().equals(user.getUserid())){
				SysUser _user = new SysUser();
				_user.setUserid(user.getUserid());
				result = checkSysUser(_user,result);
			}
			if(result.isSucc()){
				usr.setUserid(user.getUserid());
				usr.setStaffid(user.getStaffid());
				usr.setStatus(user.getStatus());
				try {
					userDao.updateSysUser(usr);
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========修改员工用户名失败,数据或数据库异常");
					e.printStackTrace();
				}
			}
			
			return result;
		}
	}
	
	public SysUser findUserByDbid(SysUser user){
		return userDao.findUserByDbid(user.getDbid());
	}

	public Result updatePassword(SysUser user,String newpwd) {
		HashMap<String, String> message = new HashMap<String, String>();
		Result result = new Result("修改密码成功！");
		SysUser usr = userDao.findUserByDbid(user.getDbid());
		if(user.getPassword().equals(newpwd)){
			result = new Result(Result.FAILURE, "新密码与旧密码相同请重新输入");
			return result;
		}
		if(!MD5String.getMD5Str(user.getPassword()).equals(usr.getPassword())){
			result = new Result(Result.FAILURE, "旧密码输入有误");
			return result;
		}
		usr.setPassword(MD5String.getMD5Str(newpwd));
		try {
			userDao.updateSysUser(usr);
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========修改密码失败,数据或数据库异常");
			e.printStackTrace();
		}
		return result;
	}

	
}
