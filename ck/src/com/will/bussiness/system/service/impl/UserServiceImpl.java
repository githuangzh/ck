package com.will.bussiness.system.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.will.bussiness.system.dao.ISysStaffDao;
import com.will.bussiness.system.dao.ISysUserDao;
import com.will.bussiness.system.entity.SysStaff;
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
	private ISysUserDao sysDao;
	@Resource
    private ISysStaffDao staffDao;
	public Pagination<SysStaff> findSysStaffByPage(
			Pagination<SysStaff> pagination,SysStaff sysStaff) {
		//List list = staffDao.findSysStaffByPage(pagination,sysStaff);
		pagination.setList(staffDao.findSysStaffByPage(pagination,sysStaff));
		return pagination;
	}
	
	/**
	 *  检查员工信息
	 * @return
	 */
	public Result checkSysStaff(SysStaff sysstaff){
		HashMap<String, String> message = new HashMap<String, String>();
		Result result = new Result("添加成功");
		SysUser user = null;
		if(null != sysstaff.getUser()){
			user = sysDao.findByUserid(sysstaff.getUser().getUserid());
		}
		SysStaff sysStaff = staffDao.findSysStaffByIdCard(sysstaff);
		if(sysStaff != null){
			message.put("idcard", "此员工已存在");
			result = new Result(Result.FAILURE, 40101, message);
		}
		if(user != null){
			message.put("userid", "用户名已存在");
			result = new Result(Result.FAILURE, 40102, message);
		}
		return result;
	}
	public Result addSysStaff(SysStaff sysstaff){
		int dbid = sysstaff.getDbid();
		if(dbid == 0 ){
			Result result = checkSysStaff(sysstaff);
			if(result.isSucc()){
				try {
					SysUser sysUser = new SysUser();
					sysUser.setUserid(sysstaff.getUserid());
					sysUser.setUsername(sysstaff.getUser().getUsername());
					sysUser.setPassword(MD5String.getMD5Str("123456"));
					sysDao.addSysUser(sysUser);
					BigDecimal pay = new BigDecimal(0.00);
					sysstaff.setPay(pay);
					staffDao.addSysStaff(sysstaff);
					return result;
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========添加员工失败,数据或数据库异常");
					e.printStackTrace();
				}
				result = new Result(Result.FAILURE, 400, "添加失败",null);
				return result;
			}
			return result;
		}else{
			Result result = new Result("修改成功");
			SysStaff staff = staffDao.findSysStaffByDbid(sysstaff);
			if(sysstaff.equals(staff)){
				result.setStatusCode(300);
				return result;
			}
			//修改身份证验证身份证
			if(!sysstaff.getIdcard().equals(staff.getIdcard())){
				SysStaff _staff = new SysStaff();
				_staff.setIdcard(sysstaff.getIdcard());
				result = checkSysStaff(_staff);
			}
			//修改用户名验证用户名
			if(!sysstaff.getUserid().equals(staff.getUserid())){
				SysStaff _staff = new SysStaff();
				SysUser _user = new SysUser();
				_user.setUserid(sysstaff.getUserid());
				_staff.setUser(_user);
				result = checkSysStaff(_staff);
				if(result.isSucc()){
					SysUser user = sysDao.findByUserid(staff.getUserid());
					user.setUserid(sysstaff.getUserid());
					try {
						sysDao.updateUserid(user);
					} catch (ResultException e) {
						logger.error(e.getMessage()+"\n=========修改员工用户名失败,数据或数据库异常");
						e.printStackTrace();
					}
				}
			}
			//用户名及身份验证成功
			if(result.isSucc()){
				try {
					staffDao.updateSysStaff(sysstaff);
					if(!sysstaff.getUser().getUsername().equals(staff.getUser().getUsername())){
						staffDao.updateSysUserName(sysstaff);
					}
					result.setMsg("修改成功");
					return result;
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========修改员工失败,数据或数据库异常");
					e.printStackTrace();
				}
				result = new Result(Result.FAILURE, 400, "添加失败",null);
				return result;
			}
			return result;
		}
	}
	
	public SysStaff findSysStaffByDbid(SysStaff staff){
		return staffDao.findSysStaffByDbid(staff);
	}

	
	public Result RemoveSysStaff(SysStaff staff){
		Result result = new Result(Result.FAILURE,"删除失败");
		try {
			int i = staffDao.removeStaff(staff);
			if(i > 0){
				SysUser user = new SysUser();
				user.setUserid(staff.getUserid());
				sysDao.removeSysUser(user);
				result.setMsg("删除成功");
				result.setSucc(Result.SUCC);
				return result;
			}
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========删除员工失败,数据或数据库异常");
			e.printStackTrace();
		}
		return result;
		
	}

	public Result UpdateSysStaffPay(SysStaff staff) {
		Result result = new Result("修改成功");
		try {
			int i = staffDao.updateStaffPay(staff);
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========员工工资失败,数据或数据库异常");
			e.printStackTrace();
		}
		return result;
	}
	
}
