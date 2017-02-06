package com.will.bussiness.ck.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import org.apache.ibatis.type.Alias;

import com.will.bussiness.system.entity.SysUser;
/**
 * 重构 2017/2/5 init
 * @author Will
 *
 */
@Alias("RlStaff")
public class RlStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	private int dbid;
	private String staffid;
	private String postid;
	private int status;
	private int gender;
	private String name;
	private String home;
	private String idcard;
	private String phone;
	private String email;
	private String qq;
	private Date jointime;
	private Date leavetime;
	private BigDecimal pay;
	
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Date getJointime() {
		return jointime;
	}
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}
	public Date getLeavetime() {
		return leavetime;
	}
	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}
	public BigDecimal getPay() {
		return pay;
	}
	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dbid;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + gender;
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result + ((idcard == null) ? 0 : idcard.hashCode());
		result = prime * result
				+ ((jointime == null) ? 0 : jointime.hashCode());
		result = prime * result
				+ ((leavetime == null) ? 0 : leavetime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pay == null) ? 0 : pay.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((postid == null) ? 0 : postid.hashCode());
		result = prime * result + ((qq == null) ? 0 : qq.hashCode());
		result = prime * result + ((staffid == null) ? 0 : staffid.hashCode());
		result = prime * result + status;
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
		RlStaff other = (RlStaff) obj;
		if (dbid != other.dbid)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender != other.gender)
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (idcard == null) {
			if (other.idcard != null)
				return false;
		} else if (!idcard.equals(other.idcard))
			return false;
		if (jointime == null) {
			if (other.jointime != null)
				return false;
		} else if (!jointime.equals(other.jointime))
			return false;
		if (leavetime == null) {
			if (other.leavetime != null)
				return false;
		} else if (!leavetime.equals(other.leavetime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pay == null) {
			if (other.pay != null)
				return false;
		} else if (!pay.equals(other.pay))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (postid == null) {
			if (other.postid != null)
				return false;
		} else if (!postid.equals(other.postid))
			return false;
		if (qq == null) {
			if (other.qq != null)
				return false;
		} else if (!qq.equals(other.qq))
			return false;
		if (staffid == null) {
			if (other.staffid != null)
				return false;
		} else if (!staffid.equals(other.staffid))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RlStaff [dbid=" + dbid + ", staffid=" + staffid + ", postid="
				+ postid + ", status=" + status + ", gender=" + gender
				+ ", name=" + name + ", home=" + home + ", idcard=" + idcard
				+ ", phone=" + phone + ", email=" + email + ", qq=" + qq
				+ ", jointime=" + jointime + ", leavetime=" + leavetime
				+ ", pay=" + pay + "]";
	}
	
}
