package com.asu.cse545.group12.dao;

import com.asu.cse545.group12.domain.Account;

public interface AccountDao {
	
	public int insertRow(Account account);

	//public List<Users> getList();

	public Account getRowById(int AccountId);

	public int updateRow(Account account);

	//public int deleteRow(int AccountId);

}
