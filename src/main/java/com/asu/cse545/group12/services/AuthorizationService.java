package com.asu.cse545.group12.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.AccessControl;
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
	
	public List<Authorization> getNotifications(Users user);

//	public List<Authorization> getNotifications();
	
	public int update(Authorization authorization);
	public Authorization getAuthorizationByTransactionId(int transactionId);
	
	public List<Authorization> getAuthorizedNotifications(int fromusername,int tousername, String reqType, String requestStatus);
	
	public Authorization getAuthorizationByAuthorizationId(int authorizationId);
	
	public int approveTransferByMerchant(int authorizationId, String userName);
	
	public List<Authorization> getApprovedPendingNotifications(Users user);
	
	public List<AccessControl> getAccessControlToView(int touser, int roleID ) ;
	
	public List<AccessControl> getAccessControlToModify(int touser, int roleID ) ;
	
	public List<AccessControl> getAccessControlToDelete(int touser, int roleID ) ;
	
	public List<AccessControl> getAccessControlToViewTransaction(int touser, int roleID ) ;
	
	public List<AccessControl> getAccessControlToModifyTransaction(int touser, int roleID ) ;
	
	public List<AccessControl> getAccessControlToDeleteTransaction(int touser, int roleID ) ;
	
	public int approvePIINotification(int authorizationId, String userName, HttpServletRequest request, HttpServletResponse response);
	
}
