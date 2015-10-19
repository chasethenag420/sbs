package com.asu.cse545.group12.services;

import java.util.Date;
import java.util.List;

import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Users;


public interface TransactionsService {

	public int doCredit(int accountNumber, int amount, String description);
	public int doDebit(int accountNumber, int amount, String description);
	public List<Transactions> searchTransactionByInternals(int accountNumber);
	public int doTransfer(int toAccountNumber, int fromAccountNumber, int amount, String description);
	public void sendOTPviaEmail(Users user);
	public List<Transactions> searchTransactionByExternals(Integer accountNum, Date toDate, Date fromDate);


}
