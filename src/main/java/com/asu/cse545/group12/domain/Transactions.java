package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSACTIONID")
	private int transactionId;

	@Column(name = "USERID")
	private int  userId;
	
	@Column(name = "TRANSACTION_STATUS")
	private String transactionStatus;
	
	
	@Column(name = "CREATION_TIMESTAMP")
	private Date creationTimestamp;
	
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;
	
	@Column(name = "MODIFIED_BY_USERID")
	private int modifiedByUserid;
	
	
	@Column(name = "MODIFIED_TIMESTAMP")
	private Date modifiedTimestamp;
	
	@Column(name = "AMOUNT")
	private int amount;
	
	@Column(name = "TRANSFER_ID")
	private int transferId;
	
	@Column(name = "TRANSACTION_KIND")
	private String transactionKind;
	
	@Column(name = "ACCOUNTNUM")
	private int accountNum;

	public int getId() {
		return transactionId;
	}

	public void setId(int id) {
		this.transactionId = id;
	}

	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getModifiedByUserid() {
		return modifiedByUserid;
	}

	public void setModifiedByUserid(int modifiedByUserid) {
		this.modifiedByUserid = modifiedByUserid;
	}

	public Date getModifiedTimestamp() {
		return modifiedTimestamp;
	}

	public void setModifiedTimestamp(Date modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public String getTransactionKind() {
		return transactionKind;
	}

	public void setTransactionKind(String transactionKind) {
		this.transactionKind = transactionKind;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	
	

}






