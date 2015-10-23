package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Users;


public interface AccountService {

	public int insertRow(int userId);
	public boolean isBalanceValid(int accountNumber, double amount,String type);
	public boolean isValidAccountNumber(int accountNumber);
	public boolean isAccountNumberBelongToUser(int accountNumber, String userName);
	public Account getAccount(String username);
	public Account getAccount(int accountNumber);
	public boolean doCredit(int accountNumber, double amount);
	public boolean doDebit(int accountNumber, double amount);
	public Account getRowById(int accountNumber);
	public int updateRow(Account account);
	public List<Account> getAccounts(int userId);
}
