package com.will.bussiness.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;
/**
 * createTime 2016/12/20
 * 表重构 changeTime 2017/2/5
 * @author Will
 *
 */
@Alias("SysUser")
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dbid;
	private String userid;
	private String staffid;
	private String password;
	private int status;
	private LinkedHashMap<String,ArrayList<SysResource>> resource;
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LinkedHashMap<String, ArrayList<SysResource>> getResource() {
		return resource;
	}
	public void setResource(LinkedHashMap<String, ArrayList<SysResource>> resource) {
		this.resource = resource;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dbid;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((staffid == null) ? 0 : staffid.hashCode());
		result = prime * result + status;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
		if (dbid != other.dbid)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (staffid == null) {
			if (other.staffid != null)
				return false;
		} else if (!staffid.equals(other.staffid))
			return false;
		if (status != other.status)
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SysUser [dbid=" + dbid + ", userid=" + userid + ", staffid="
				+ staffid + ", password=" + password + ", status=" + status
				+ ", resource=" + resource + "]";
	}
	
}
