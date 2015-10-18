package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Users;


public interface TransactionsService {

	public int doCredit(int accountNumber, int amount);
	public int doDebit(int accountNumber, int amount);
	public List<Transactions> searchTransactionByInternals(int accountNumber);
	public int doTransfer(int toAccountNumber, int fromAccountNumber, int amount);
	public void sendOTPviaEmail(Users user);
}
