package com.will.bussiness.system.service;

import org.springframework.stereotype.Service;

import com.will.bussiness.system.entity.SysStaff;
import com.will.utility.Pagination;
import com.will.utility.Result;

public interface IUserService {
	public Pagination<SysStaff> findSysStaffByPage(Pagination<SysStaff> pagination,SysStaff staff);
	/**
	 * 添加用户
	 * @param sysstaff
	 * @return
	 */
	public Result addSysStaff(SysStaff sysstaff);
	
	/**
	 * 根据dbid查询用户
	 * @param staff
	 * @return
	 */
	public SysStaff findSysStaffByDbid(SysStaff staff);
	
	
	/**
	 * 删除员工
	 * @param staff
	 * @return
	 */
	public Result RemoveSysStaff(SysStaff staff);
	
	public Result UpdateSysStaffPay(SysStaff staff);
}