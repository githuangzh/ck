package com.will.bussiness.system.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("SysRole")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private int dbid;
	private String roleid;
	private String rolename;
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}
