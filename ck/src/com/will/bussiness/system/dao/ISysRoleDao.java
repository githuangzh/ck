package com.will.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysRole;
import com.will.bussiness.system.entity.SysUser;
import com.will.exception.ResultException;
import com.will.utility.Pagination;
import com.will.utility.SupperMapper;


@SupperMapper
public interface ISysRoleDao {
	public List<SysRole> findRolesByUserid(String userid);

	public List<SysRole> findSysRoleByPage(@Param("page")Pagination<SysRole> pagination,
			@Param("role")SysRole sysRole);

	public int addRole(SysRole sysRole) throws ResultException;

	public int updateRole(SysRole sysRole) throws ResultException;;

	public int removeRole(SysRole sysRole) throws ResultException;;

	public int removeRoleWithResource(SysRole sysRole) throws ResultException;;
	
	public int removeRoleWithUser(SysRole sysRole) throws ResultException;;

	public int addRoleWithUserByArray(String[] userIds,@Param("role") SysRole sysRole) throws ResultException;;

	public int addRoleWithResourceByArray(String[] resIds,@Param("role") SysRole sysRole) throws ResultException;;

	public List<SysUser> findRoleWithUser(SysRole sysRole);

	public List<SysResource> findRoleWithResurce(SysRole sysRole);

	public SysRole findSysRoleByRoleid(SysRole sysRole);
	
	public SysRole findSysRoleByDbid(SysRole sysRole);
	
	public SysResource findSysResourcesByRoleid(SysRole sysRole);
}
