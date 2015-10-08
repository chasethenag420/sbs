package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

public interface AuthorizationDao {
	
	public int insertRow(Authorization authorization);
	
	public int approve(Authorization authorization);

	public int reject(Authorization authorization);

	public int forward(Authorization authorization);
	
	public List<Authorization> getNotifications(); 

	//public int deleteRow(int AccountId);
}
