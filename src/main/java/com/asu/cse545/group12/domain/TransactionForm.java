package com.asu.cse545.group12.domain;

public class TransactionForm
{
	String toAccount, fromAccount, amount;
	String description, transactionType;
	String fromDate, toDate;
	
	
	
	

	public String getToAccount() {
		return toAccount;
	}



	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}



	public String getFromAccount() {
		return fromAccount;
	}



	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	
	public String getFromDate() {
		return fromDate;
	}



	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}



	public String getToDate() {
		return toDate;
	}



	public void setToDate(String toDate) {
		this.toDate = toDate;
	}



	@Override
	public String toString() {
		return "TransactionForm [toAccount=" + toAccount + ", fromAccount=" + fromAccount + ", amount=" + amount
				+ ", description=" + description + ", transactionType=" + transactionType + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}



	
	
	
}