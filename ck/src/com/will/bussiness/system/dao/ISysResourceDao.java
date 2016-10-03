package com.will.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysStaff;
import com.will.bussiness.system.entity.SysUser;
import com.will.exception.ResultException;
import com.will.utility.Pagination;
import com.will.utility.SupperMapper;

@SupperMapper
public interface ISysResourceDao {

	public List<SysResource> findSysResourceByUserId(String userid);
	
	/**
	 * 查询资源--表格
	 * @param pagination
	 * @return
	 */
	public List<SysResource> findSysResourceByPage(@Param("page")Pagination<SysResource> pagination,@Param("resource")SysResource resource);

	/**
	 * 添加资源
	 * @param sysResource
	 * @return
	 * @throws ResultException
	 */
	public int addSysResource(SysResource sysResource) throws ResultException;
	/**
	 * 修改资源
	 * @param sysResource
	 * @return
	 * @throws ResultException
	 */
	public int updateResource(SysResource sysResource) throws ResultException;
	/**
	 * 删除资源
	 * @param sysResource
	 * @return
	 * @throws ResultException
	 */
	public int deleteResource(SysResource sysResource) throws ResultException;
	
	
	/**
	 * 查询资源--根据dbid
	 * @param sysResource
	 * @return
	 */
	public SysResource findSysResourceByDbid(SysResource sysResource);
	
	/**
	 * 查询资源--根据resid
	 * @param sysResource
	 * @return
	 */
	public SysResource findSysResourceByResid(SysResource sysResource);
	
	/**
	 * 查询资源--根据resname
	 * @param sysResource
	 * @return
	 */
	public SysResource findSysResourceByResname(SysResource sysResource);
	/**
	 * 查询管理资源角色数量
	 * @param sysResource
	 * @return
	 */
	public int findSysResourceByRoleCount(SysResource sysResource);

	public List<String> getResourceTopid();

}
