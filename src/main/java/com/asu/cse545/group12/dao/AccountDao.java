package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

public interface AccountDao {
	
	public int insertRow(Account account);

	//public List<Users> getList();

	public Account getRowById(int AccountId);
	public Account getAccountByUserId(int userId);

	public int updateRow(Account account);
	public Account getAccountByAccountNumber(int accountNumber);
	//public int deleteRow(int AccountId);
	public List<Account> getAccountsByUserId(int userId) ;

}
