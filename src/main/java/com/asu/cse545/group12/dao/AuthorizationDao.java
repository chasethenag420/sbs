package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;

public interface AuthorizationDao {

	public int approve(Account account);

	public int reject(int AccountId);

	public int forward(int AccountId);
	
	public List<Authorization> getNotifications(); 

	//public int deleteRow(int AccountId);
}
