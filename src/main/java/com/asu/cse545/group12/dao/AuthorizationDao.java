package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;

public interface AuthorizationDao {

	public boolean approve(Account account);

	public boolean reject(int AccountId);

	public boolean forward(int AccountId);
	
	public boolean view(Account account);
	
	public List<Authorization> getNotifications(); 

	//public int deleteRow(int AccountId);
}
