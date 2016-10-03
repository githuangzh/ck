package com.will.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.will.bussiness.system.entity.SysStaff;
import com.will.bussiness.system.entity.SysUser;
import com.will.exception.ResultException;
import com.will.utility.Pagination;
import com.will.utility.SupperMapper;

@SupperMapper
public interface ISysStaffDao {
	/**
	 * 查询员工--表格
	 * @param pagination
	 * @return
	 */
	public List<SysStaff> findSysStaffByPage(@Param("page")Pagination<SysStaff> pagination,@Param("staff")SysStaff staff);
	/**
	 * 添加员工
	 * @param sysuser
	 */
	public int addSysStaff(SysStaff sysstaff) throws ResultException;
	
	/**
	 * 根据身份证查询员工
	 * @param sysStaff
	 * @return
	 */
	public SysStaff findSysStaffByIdCard(SysStaff sysStaff);
	
	/**
	 * 根据dbid查询员工
	 * @param sysStaff
	 * @return
	 */
	public SysStaff findSysStaffByDbid(SysStaff sysStaff);
	
	
	/**
	 * 更新员工数据
	 * @return
	 * @throws ResultException
	 */
	public int updateSysStaff(SysStaff staff) throws ResultException;
	
	/**
	 * 更新员工姓名
	 * @param user
	 * @return
	 * @throws ResultException
	 */
	public int updateSysUserName(SysStaff staff) throws ResultException;
	
	
	/**
	 * 更改员工工资
	 * @param staff
	 * @return
	 * @throws ResultException
	 */
	public int updateStaffPay(SysStaff staff) throws ResultException;


	/**
	 * 移除员工
	 * @param staff
	 * @return
	 * @throws ResultException
	 */
	public int removeStaff(SysStaff staff) throws ResultException;
}
