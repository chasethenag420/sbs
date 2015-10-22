package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

public interface AuthorizationService {

	//*************************************************************************************************
	//              IN all this methods the Authorization ID will be the input , 
	//										Update the Flags based on the Authorization ID
	//*************************************************************************************************

	public int signupInsertRow(Users users);
	
	public int approve(int authorizationId, String userName);

	public int reject(int authorizationId, String userName);

	public int forward(int authorizationId, String userName);
	
	public int regularEmpRequest(Authorization authorization);
	
	public List<Authorization> getNotifications();
	
	public int update(Authorization authorization);
	public List<Authorization> getAuthorizedNotifications(int fromusername,int tousername);
}
