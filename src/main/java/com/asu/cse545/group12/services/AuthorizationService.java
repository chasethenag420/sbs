package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;

public interface AuthorizationService {

	//*************************************************************************************************
	//              IN all this methods the Authorization ID will be the input , 
	//										Update the Flags based on the Authorization ID
	//*************************************************************************************************

	
	public int approve(Authorization authorization);

	public int reject(Authorization authorization);

	public int forward(Authorization authorization);
	
	public List<Authorization> getNotifications(); 
}
