package com.asu.cse545.group12.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="role")
public class Role{
	@Id
	@Column(name="ROLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int roleId;
	
	@Column(name="ROLEDESCRIPTION")
	private String roleDescription;
	
	@Column(name="VIEWFLAG")
	private int viewFlag;
	
	@Column(name="MODIFYFLAG")
	private int modifyFlag;
	
	@Column(name="CREATEFLAG")
	private int createFlag;
	
	@Column(name="DELETEFLAG")
	private int deleteFlag;
	
	@Column(name="ROLEID")
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Column(name="ROLEDESCRIPTION")
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	@Column(name="VIEWFLAG")
	public int getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(int viewFlag) {
		this.viewFlag = viewFlag;
	}
	@Column(name="MODIFYFLAG")
	public int getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(int modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	@Column(name="CREATEFLAG")
	public int getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(int createFlag) {
		this.createFlag = createFlag;
	}
	@Column(name="DELETEFLAG")
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}