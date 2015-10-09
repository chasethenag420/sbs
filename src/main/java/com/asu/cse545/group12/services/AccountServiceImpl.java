package com.asu.cse545.group12.services;

import java.util.Calendar;

import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Users;

public class AccountServiceImpl implements AccountService {

	AccountDao accountdao;
	@Override
	public int insertRow(int userId) {
		// TODO Auto-generated method stub
		// check if the user request for creation got approved
		Account account = new Account();
		account.setAccountType("checkings");
		account.setActiveFlag(1);
		account.setBalance(0);
		account.setCreationDate(Calendar.getInstance().getTime());
		account.setModifiedTimeStamp(Calendar.getInstance().getTime());
		account.setUserId(userId);
		accountdao.insertRow(account);
		return 0;
	}

	@Override
	public Account getRowById(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRow(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

}
