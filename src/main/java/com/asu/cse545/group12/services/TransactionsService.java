package com.asu.cse545.group12.services;

import java.util.List;


public interface TransactionsService {

	public boolean doCredit(int accountNumber, int amount);
	public boolean doDebit(int accountNumber, int amount);
}
