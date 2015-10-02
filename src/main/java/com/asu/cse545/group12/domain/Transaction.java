package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSACTIONID")
	private int id;

	@Column(name = "USERID")
	private int  userid;
	
	@Column(name = "TRANSACTION_STATUS")
	private String transaction_status;
	
	
	@Column(name = "CREATION_TIMESTAMP")
	private Date creation_timestamp;
	
	@Column(name = "TRANSACTION_TYPE")
	private String transaction_type;
	
	@Column(name = "MODIFIED_BY_USERID")
	private int modified_by_userid;
	
	
	@Column(name = "MODIFIED_TIMESTAMP")
	private Date modified_timestamp;
	
	@Column(name = "AMOUNT")
	private int amount;
	
	@Column(name = "TRANSFER_ID")
	private int transferid;
	
	@Column(name = "TRANSACTION_KIND")
	private String transaction_kind;
	
	@Column(name = "ACCOUNTNUM")
	private int accountnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public Date getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Date creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public int getModified_by_userid() {
		return modified_by_userid;
	}

	public void setModified_by_userid(int modified_by_userid) {
		this.modified_by_userid = modified_by_userid;
	}

	public Date getModified_timestamp() {
		return modified_timestamp;
	}

	public void setModified_timestamp(Date modified_timestamp) {
		this.modified_timestamp = modified_timestamp;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTransferid() {
		return transferid;
	}

	public void setTransferid(int transferid) {
		this.transferid = transferid;
	}

	public String getTransaction_kind() {
		return transaction_kind;
	}

	public void setTransaction_kind(String transaction_kind) {
		this.transaction_kind = transaction_kind;
	}

	public int getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(int accountnum) {
		this.accountnum = accountnum;
	}
	
	
	

}






