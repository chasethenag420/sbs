package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="accesscontrol")
public class AccessControl{
	
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="USERID")
	private int userId;
	
	@Column(name="VIEWPROFILE")
	private int view_external_profile;
	
	@Column(name="MODIFYEXTERNALUSER")
	private int modify_external_profile;
	
	@Column(name="DELETEEXTERNALUSER")
	private int delete_external_profile;
	
	@Column(name="CANCELTRANSACTION")
	private int cancel_transaction;
	
	@Column(name="VIEWTRANSACTION")
	private int view_transaction;
	
	@Column(name="MODIFYTRANSACTION")
	private int modify_transaction;

	@Column(name="Id")
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	@Column(name="USERID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="VIEWPROFILE")
	public int getView_external_profile() {
		return view_external_profile;
	}

	public void setView_external_profile(int view_external_profile) {
		this.view_external_profile = view_external_profile;
	}

	@Column(name="MODIFYEXTERNALUSER")
	public int getModify_external_profile() {
		return modify_external_profile;
	}

	public void setModify_external_profile(int modify_external_profile) {
		this.modify_external_profile = modify_external_profile;
	}

	@Column(name="DELETEEXTERNALUSER")
	public int getDelete_external_profile() {
		return delete_external_profile;
	}

	public void setDelete_external_profile(int delete_external_profile) {
		this.delete_external_profile = delete_external_profile;
	}

	@Column(name="CANCELTRANSACTION")
	public int getCancel_transaction() {
		return cancel_transaction;
	}

	public void setCancel_transaction(int cancel_transaction) {
		this.cancel_transaction = cancel_transaction;
	}

	@Column(name="VIEWTRANSACTION")
	public int getView_transaction() {
		return view_transaction;
	}

	public void setView_transaction(int view_transaction) {
		this.view_transaction = view_transaction;
	}

	@Column(name="MODIFYTRANSACTION")
	public int getModify_transaction() {
		return modify_transaction;
	}

	public void setModify_transaction(int modify_transaction) {
		this.modify_transaction = modify_transaction;
	}

	@Override
	public String toString() {
		return "AccessControl [Id=" + Id + ", userId=" + userId + ", view_external_profile=" + view_external_profile
				+ ", modify_external_profile=" + modify_external_profile + ", delete_external_profile="
				+ delete_external_profile + ", cancel_transaction=" + cancel_transaction + ", view_transaction="
				+ view_transaction + ", modify_transaction=" + modify_transaction + "]";
	}
	
	
	
	
}