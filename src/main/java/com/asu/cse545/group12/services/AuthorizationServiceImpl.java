package com.asu.cse545.group12.services;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.TransactionDaoImpl;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Users;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private static final Logger logger = Logger.getLogger(AuthorizationServiceImpl.class);
	@Autowired
	AuthorizationDao authorizationDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	AccountService accountService;
	
	@Override
	public int approve(int authorizationId, String userName) {
		// TODO Auto-generated method stub
		Authorization authorization=authorizationDao.getAuthorizationByAuthorizationId(authorizationId);
		//based on type of transaction trigger relavent action
		if("Signup".equals(authorization.getRequestType())){
			Users approver=userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus("Complete");
			Users requestor=userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			requestor.setUserStatus("active");
			userDao.updateRow(requestor);
			//create an account for this user by default checkings
			accountService.insertRow(requestor.getUserId());
		}
		else if("Credit".equals(authorization.getRequestType())){
			Users approver=userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus("Complete");
			
			
			Users requestor=userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			if(logger.isDebugEnabled()){
				logger.debug("**********************************requestor: "+requestor.toString());
			}
			Account account = accountService.getAccount(requestor.getUserName());
			if(logger.isDebugEnabled()){
				logger.debug("**********************************authorization: "+authorization.toString());
				logger.debug("**********************************Account: "+account.toString());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId()); 
			accountService.doCredit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus("Complete");
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);
			//requestor.setUserStatus("active");
			//userDao.updateRow(requestor);
			//create an account for this user by default checkings
			//accountService.insertRow(requestor.getUserId());
		}
		
		else if("Debit".equals(authorization.getRequestType())){
			Users approver=userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus("Complete");
			
			
			Users requestor=userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			Account account = accountService.getAccount(requestor.getUserName());
			if(logger.isDebugEnabled()){
				logger.debug("**********************************TransactionId: "+authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId()); 
			accountService.doDebit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus("Complete");
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);
			//requestor.setUserStatus("active");
			//userDao.updateRow(requestor);
			//create an account for this user by default checkings
			//accountService.insertRow(requestor.getUserId());
		}
		return authorizationDao.approve(authorization);
	}

	@Override
	public List<Authorization> getNotifications() {
		// TODO Auto-generated method stub
		return authorizationDao.getNotifications();
	}

	@Override
	public int reject(Authorization authorization) {
		// TODO Auto-generated method stub
		return authorizationDao.reject(authorization);
	}

	@Override
	public int forward(Authorization authorization) {
		// TODO Auto-generated method stub
		return authorizationDao.forward(authorization);
	}

	@Override
	public int signupInsertRow(Users user) {
		Authorization authorizationRequest = new Authorization();
		authorizationRequest.setAuthorizedToUserId(user.getUserId());
		authorizationRequest.setRequestStatus("Pending");
		authorizationRequest.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
		authorizationRequest.setRequestDescription("Approval for account creation");
		authorizationRequest.setRequestType("Signup");
		return authorizationDao.insertRow(authorizationRequest);		
	}

	@Override
	public int regularEmpRequest(Authorization authorization) {
		authorizationDao.insertRow(authorization);
		return 0;
	}
	
	

}
