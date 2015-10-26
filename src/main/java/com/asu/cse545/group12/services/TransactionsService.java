package com.asu.cse545.group12.services;

import java.util.Date;
import java.util.List;

import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Users;


public interface TransactionsService {

	public int doCredit(int accountNumber, double amount, String description);
	public int doDebit(int accountNumber, double amount, String description);
	public List<Transactions> searchTransactionByInternals(int accountNumber);
	public int doTransfer(int fromAccountNumber, int toAccountNumber, double amount, String description);
	public void sendOTPviaEmail(Users user);
	public List<Transactions> searchTransactionByExternals(Integer accountNum, Date toDate, Date fromDate);
	public int deleteTransaction(int transactionId);
	public int payMerchant(int fromAccountNumber, int toAccountNumber, double amount, String description, String customerDetails);
}
