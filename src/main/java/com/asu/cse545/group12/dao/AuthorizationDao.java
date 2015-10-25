package com.asu.cse545.group12.dao;

import java.util.List;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

public interface AuthorizationDao {
	
	public int insertRow(Authorization authorization);
	
	public int updateRow(Authorization authorization);
	
	public int approve(Authorization authorization);

	public int reject(Authorization authorization);

	public int forward(Authorization authorization);
	
	//public List<Authorization> getNotifications(); 
	
	public List<Authorization> getAuthorizednotifications(int fromuser , int touser, String reqType, String requestStatus);

	//public int deleteRow(int AccountId);
	public Authorization getRowById(int authorizationId);
	
	public Authorization getAuthorizationByAuthorizationId(int authorizationId);
	
	public Authorization getAuthorizationByTransactionId(int transactionId);

	public List<Authorization> getNotificationsForExternal(Users user);

	public List<Authorization> getNotificationsForMerchant(Users user);
	
	public List<Authorization> getNotificationsForRegular(Users user);
	
	public List<Authorization> getNotificationsForManager(Users user);
	
	public List<Authorization> getNotificationsForAdmin(Users user);

	public List<Authorization> getApprovedPendingNotificationsForManager(Users user);

	public List<Authorization> getApprovedPendingNotificationsForAdmin(Users user);

	public List<Authorization> getApprovedPendingNotificationsForRegular(Users user);

	public List<Authorization> getApprovedPendingNotificationsForMerchant(Users user);

	public List<Authorization> getApprovedPendingNotificationsForExternal(Users user);
	
}
