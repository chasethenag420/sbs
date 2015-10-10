package com.asu.cse545.group12.services;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Users;


public interface AccountService {

	public int insertRow(int userId);
	public boolean isBalanceValid(int accountNumber, int amount,String type);
	public boolean isValidAccountNumber(int accountNumber);
	public boolean isAccountNumberBelongToUser(int accountNumber, String userName);
	public Account getAccount(String username);
	public Account getAccount(int accountNumber);
	public boolean doCredit(int accountNumber, int amount);
	public boolean doDebit(int accountNumber, int amount);
	public Account getRowById(int accountNumber);
	public int updateRow(Account account);
}
