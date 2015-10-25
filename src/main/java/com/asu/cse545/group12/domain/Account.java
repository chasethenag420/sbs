package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="account")
public class Account{
	
	@Column(name="USERID")
	private int userId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCOUNTNUM")
	private int accountNumber;
	
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", activeFlag=" + activeFlag + ", balance=" + balance + ", creationDate=" + creationDate
				+ ", modifiedTimeStamp=" + modifiedTimeStamp + "]";
	}
	@Column(name="ACCOUNT_TYPE")
	private String accountType;
	
	@Column(name="ACTIVE_FLAG")
	private int activeFlag;
	
	@Column(name="BALANCE")
	private double balance;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="MODIFIED_TIMESTAMP")
	private Date modifiedTimeStamp;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}
	public void setModifiedTimeStamp(Date modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}	
}