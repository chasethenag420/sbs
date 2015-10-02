package com.asu.cse545.group12.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Transfer {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSFERID")
	private int transferid;

	@Column(name = "TRANSACTION_STATUS")
	private String transaction_status;
	
	@Column(name = "USER_TO_TRANSACTIONID")
	private int user_to_transactionid;
	
	@Column(name = "USER_FROM_TRANSACTIONID")
	private int user_from_transactionid;

	public int getTransferid() {
		return transferid;
	}

	public void setTransferid(int transferid) {
		this.transferid = transferid;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public int getUser_to_transactionid() {
		return user_to_transactionid;
	}

	public void setUser_to_transactionid(int user_to_transactionid) {
		this.user_to_transactionid = user_to_transactionid;
	}

	public int getUser_from_transactionid() {
		return user_from_transactionid;
	}

	public void setUser_from_transactionid(int user_from_transactionid) {
		this.user_from_transactionid = user_from_transactionid;
	}

}


