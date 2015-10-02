package com.asu.cse545.group12.domain;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role{
	@Column(name="ROLEID")
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
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public int getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(int viewFlag) {
		this.viewFlag = viewFlag;
	}
	public int getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(int modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public int getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(int createFlag) {
		this.createFlag = createFlag;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}