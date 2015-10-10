package com.asu.cse545.group12.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Transfer {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSFERID")
	private int transferId;

	@Column(name = "TRANSACTION_STATUS")
	private String transactionStatus;
	
	@Column(name = "USER_TO_TRANSACTIONID")
	private int userToTransactionid;
	
	@Column(name = "USER_FROM_TRANSACTIONID")
	private int userFromTransactionid;

	public int getTransferid() {
		return transferId;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public int getUserToTransactionid() {
		return userToTransactionid;
	}

	public void setUserToTransactionid(int userToTransactionid) {
		this.userToTransactionid = userToTransactionid;
	}

	public int getUserFromTransactionid() {
		return userFromTransactionid;
	}

	public void setUserFromTransactionid(int userFromTransactionid) {
		this.userFromTransactionid = userFromTransactionid;
	}

	@Override
	public String toString() {
		return "Transfer [transferId=" + transferId + ", transactionStatus=" + transactionStatus
				+ ", userToTransactionid=" + userToTransactionid + ", userFromTransactionid=" + userFromTransactionid
				+ "]";
	}

	
}


