package com.will.bussiness.system.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("SysResource")
public class SysResource implements Serializable{
	private static final long serialVersionUID = 1L;
	/**dbid*/
	private int dbid;
	/**资源编号*/
	private String resid;
	/**名称*/
	private String resname;
	/**上级id*/
	private String parentid;
	/**url*/
	private String url;
	/**标记*/
	private String icon;
	/**排序*/
	private String ordernum;
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	@Override
	public String toString() {
		return "SysResource [dbid=" + dbid + ", resid=" + resid + ", resname="
				+ resname + ", parentid=" + parentid + ", url=" + url
				+ ", ico=" + icon + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dbid;
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result
				+ ((ordernum == null) ? 0 : ordernum.hashCode());
		result = prime * result
				+ ((parentid == null) ? 0 : parentid.hashCode());
		result = prime * result + ((resid == null) ? 0 : resid.hashCode());
		result = prime * result + ((resname == null) ? 0 : resname.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		SysResource other = (SysResource) obj;
		if (dbid != other.dbid)
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (ordernum == null) {
			if (other.ordernum != null)
				return false;
		} else if (!ordernum.equals(other.ordernum))
			return false;
		if (parentid == null) {
			if (other.parentid != null)
				return false;
		} else if (!parentid.equals(other.parentid))
			return false;
		if (resid == null) {
			if (other.resid != null)
				return false;
		} else if (!resid.equals(other.resid))
			return false;
		if (resname == null) {
			if (other.resname != null)
				return false;
		} else if (!resname.equals(other.resname))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	
}
