package com.will.bussiness.ck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.will.bussiness.ck.entity.RlStaff;
import com.will.bussiness.system.entity.SysUser;
import com.will.exception.ResultException;
import com.will.utility.Pagination;
import com.will.utility.SupperMapper;

@SupperMapper
public interface IRlStaffDao {
	/**
	 * 查询员工--表格
	 * @param pagination
	 * @return
	 */
	public List<RlStaff> findRlStaffByPage(@Param("page")Pagination<RlStaff> pagination,@Param("staff")RlStaff staff);
	/**
	 * 添加员工
	 * @param sysuser
	 */
	public int addRlStaff(RlStaff rlStaff) throws ResultException;
	
	/**
	 * 根据身份证查询员工
	 * @param RlStaff
	 * @return
	 */
	public RlStaff findRlStaffByIdCard(RlStaff rlStaff);
	
	/**
	 * 根据dbid查询员工
	 * @param RlStaff
	 * @return
	 */
	public RlStaff findRlStaffByDbid(RlStaff rlStaff);
	
	
	/**
	 * 更新员工数据
	 * @return
	 * @throws ResultException
	 */
	public int updateRlStaff(RlStaff staff) throws ResultException;
	
	/**
	 * 更新员工姓名
	 * @param user
	 * @return
	 * @throws ResultException
	 */
	public int updateSysUserName(RlStaff staff) throws ResultException;
	
	
	/**
	 * 更改员工工资
	 * @param staff
	 * @return
	 * @throws ResultException
	 */
	public int updateStaffPay(RlStaff staff) throws ResultException;


	/**
	 * 移除员工
	 * @param staff
	 * @return
	 * @throws ResultException
	 */
	public int removeStaff(RlStaff staff) throws ResultException;
}
