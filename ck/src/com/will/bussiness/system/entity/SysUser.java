package com.will.bussiness.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;
@Alias("SysUser")
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dbid;
	private String userid;
	private String username;
	private String password;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Override
	public String toString() {
		return "SysUser [dbid=" + dbid + ", userid=" + userid + ", username="
				+ username + ", password=" + password + ", resource="
				+ resource + "]";
	}
	
}
