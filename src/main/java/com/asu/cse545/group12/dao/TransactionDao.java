package com.asu.cse545.group12.dao;

import java.util.Date;
import java.util.List;

import com.asu.cse545.group12.domain.Transactions;



public interface TransactionDao {
	public int insertRow(Transactions transaction);
	public Transactions getRowById(int transactionId);
	public int updateRow(Transactions transaction);
	public Transactions getTransactionByTransactionId(int transactionId);
    public List<Transactions> getTransactionsByAccNum(int accountNumber);
    public List<Transactions> getTransactionsBetweenDates(Integer accountNum, String fromDate, String toDate);
    public List<Transactions> getTransactionsByDate(Integer accountNum,Date toDate,Date fromDate);
	public int deleteTransaction(int transactionId);


}