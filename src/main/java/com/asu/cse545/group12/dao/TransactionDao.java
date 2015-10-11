package com.asu.cse545.group12.dao;

import com.asu.cse545.group12.domain.Transactions;



public interface TransactionDao {
	public int insertRow(Transactions transaction);
	public Transactions getRowById(int transactionId);
	public int updateRow(Transactions transaction);
	public Transactions getTransactionByTransactionId(int transactionId);
/*	public List<User> getList();

	public User getRowById(int id);

	

	*/
}