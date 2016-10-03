package com.will.bussiness.system.service;

import java.util.List;

import com.will.bussiness.system.entity.SysResource;
import com.will.utility.Pagination;
import com.will.utility.Result;

public interface IResourceService {
	/**
	 * 查询资源--表格
	 * @param pagination
	 * @param resource
	 * @return
	 */
	public Pagination<SysResource> findSysResourceByPage(Pagination<SysResource> pagination
			,SysResource resource);

	/**
	 * 查询资源--根据dbid
	 * @param res
	 * @return
	 */
	public SysResource findSysResourceByDbid(SysResource res);

	/**
	 * 添加及修改资源
	 * @param resource
	 * @return
	 */
	public Result addSysResource(SysResource resource);

	public Result RemoveSysResource(SysResource resource);

	public List<String> getResourceTopid();
}
