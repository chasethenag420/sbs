package com.asu.cse545.group12.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Searchform {
	String toDate, fromDate;
	Integer accountNumber;
	
	
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
