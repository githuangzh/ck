package com.will.bussiness.system.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.will.bussiness.common.Constant;
import com.will.bussiness.system.dao.ISysResourceDao;
import com.will.bussiness.system.entity.SysResource;
import com.will.bussiness.system.entity.SysStaff;
import com.will.bussiness.system.entity.SysUser;
import com.will.bussiness.system.service.IResourceService;
import com.will.exception.ResultException;
import com.will.utility.Pagination;
import com.will.utility.Result;

@Service
public class ResourceServiceImpl implements IResourceService{
	private static Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	@Resource
	private ISysResourceDao sysResourceDao;
	
	public Pagination<SysResource> findSysResourceByPage(Pagination<SysResource> pagination
			,SysResource resource){
		pagination.setList(sysResourceDao.findSysResourceByPage(pagination, resource));
		return pagination;
	}

	public SysResource findSysResourceByDbid(SysResource res) {
		return sysResourceDao.findSysResourceByDbid(res);
	}

	//检测新建或修改资源
	public Result checkSysResourece(SysResource resource,Result result){
		HashMap<String, String> message = new HashMap<String, String>();
		SysResource resid = null;
		resid = sysResourceDao.findSysResourceByResid(resource);
		if(resid != null){
			message.put("resid", "资源编号已存在");
			result = new Result(Result.FAILURE, 40101, message);
			return result;
		}
		SysResource resname = null;
		resname = sysResourceDao.findSysResourceByResname(resource);
		if(resname != null){
			message.put("resname", "资源名称已存在");
			result = new Result(Result.FAILURE, 40102, message);
			return result;
		}
		return result;
	}
	public Result addSysResource(SysResource resource) {
		int dbid = resource.getDbid();
		if(dbid == 0){
			//新建资源
			Result result = new Result("添加成功");
			result = checkSysResourece(resource,result);
			if(result.isSucc()){
				try {
					sysResourceDao.addSysResource(resource);
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========添加资源失败,数据或数据库异常");
					e.printStackTrace();
				}
			}
			return result;
		}else{
			//修改资源
			Result result = new Result("修改成功");
			SysResource res = sysResourceDao.findSysResourceByDbid(resource);
			if(resource.equals(res)){
				result.setStatusCode(300);
				return result;
			}
			//验证资源
			result = checkSysResourece(resource,result);
			if(result.isSucc()){
				try {
					sysResourceDao.updateResource(resource);
				} catch (ResultException e) {
					logger.error(e.getMessage()+"\n=========修改资源失败,数据或数据库异常");
					e.printStackTrace();
				}
			}
			return result;
		}
	}

	public Result RemoveSysResource(SysResource resource) {
		Result result = new Result("删除成功!");
		int rolecount = sysResourceDao.findSysResourceByRoleCount(resource);
		if(rolecount > 0){
			result = new Result(Result.FAILURE,Constant.RESULT_FAILD, "不可删除,有关联角色");
			return result;
		}
		try {
			sysResourceDao.deleteResource(resource);
		} catch (ResultException e) {
			logger.error(e.getMessage()+"\n=========删除资源失败,数据或数据库异常");
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getResourceTopid() {
		return sysResourceDao.getResourceTopid();
	}
	
	
}
