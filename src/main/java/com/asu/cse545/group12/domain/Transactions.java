package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
@Entity(name="transaction")
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSACTIONID")
	private int transactionId;
	
	
	@Pattern(regexp="^[a-zA-Z0-9äöüÄÖÜ]*$", message="Enter correct username")
	@Column(name = "USERID")
	private int  userId;
	
	@Column(name = "TRANSACTION_STATUS")
	private String transactionStatus;
	
	@Column(name = "ACCOUNTNUM")
	private int accountNumber;
	
	@Column(name = "CREATION_TIMESTAMP")
	private Date creationTimestamp;
	
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;
	
	@Column(name = "MODIFIED_BY_USERID")
	private int modifiedByUserid;
	
	
	@Column(name = "MODIFIED_TIMESTAMP")
	private Date modifiedTimestamp;
	
	@Pattern(regexp="^[0-9]+\\.?[0-9]*$", message="Enter correct amount")
	@Column(name = "AMOUNT")
	private int amount;
	
	@Column(name = "TRANSFER_ID")
	private int transferId;
	
	@Column(name = "TRANSACTION_KIND")
	private String severity;

	@Column(name = "TRANSACTION_DESCRIPTION")
	private String transactionDescription;
	
	
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	
	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", userId=" + userId + ", transactionStatus="
				+ transactionStatus + ", accountNumber=" + accountNumber + ", creationTimestamp=" + creationTimestamp
				+ ", transactionType=" + transactionType + ", modifiedByUserid=" + modifiedByUserid
				+ ", modifiedTimestamp=" + modifiedTimestamp + ", amount=" + amount + ", transferId=" + transferId
				+ ", severity=" + severity + ", transactionDescription=" + transactionDescription + "]";
	}

	

}






