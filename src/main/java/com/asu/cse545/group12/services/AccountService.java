package com.asu.cse545.group12.services;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Users;


public interface AccountService {
	
	public int insertRow(int userId);

	
		public Account getRowById(int accountId);

		public int updateRow(Account account);

		


}
