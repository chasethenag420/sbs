package com.asu.cse545.group12.services;


import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asu.cse545.group12.hashing.HashGenerator;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;

@Service("TransactionsServiceImpl")
public class TransactionsServiceImpl implements TransactionsService {
	private static final Logger logger = Logger.getLogger(TransactionsServiceImpl.class);

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	AuthorizationDao authorizationDao;
	

	public boolean doCredit(int accountNumber, int amount){
		boolean creditStatus = accountService.isBalanceValid(accountNumber, amount, "credit");
		if(creditStatus==true){
			Account account = accountService.getAccount(accountNumber);
			Transactions transaction = new Transactions();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(amount);
			transaction.setCreationTimestamp(Calendar.getInstance().getTime());
			transaction.setTransactionStatus("pending");
			transaction.setUserId(account.getUserId());
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setSeverity("critical");
			transaction.setTransactionType("credit");
			transactionDao.insertRow(transaction);
			
			Authorization authorization = new Authorization();
			authorization.setAuthorizedByUserId(account.getUserId());
			authorization.setRequestStatus("Pending");
			authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			authorization.setRequestDescription("Approval for amount credit");
			authorization.setRequestType("credit");
			authorizationDao.insertRow(authorization);
		}
		return creditStatus;
	}

	public boolean doDebit(int accountNumber, int amount){
		boolean debitStatus = accountService.isBalanceValid(accountNumber, amount, "debit");
		if(debitStatus==true){
			Account account = accountService.getAccount(accountNumber);
			Transactions transaction = new Transactions();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(amount);
			transaction.setCreationTimestamp(Calendar.getInstance().getTime());
			transaction.setTransactionStatus("pending");
			transaction.setUserId(account.getUserId());
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setSeverity("critical");
			transaction.setTransactionType("debit");
			transactionDao.insertRow(transaction);
			
			Authorization authorization = new Authorization();
			authorization.setAuthorizedByUserId(account.getUserId());
			authorization.setRequestStatus("Pending");
			authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			authorization.setRequestDescription("Approval for amount debit");
			authorization.setRequestType("debit");
			authorizationDao.insertRow(authorization);
		}
		return debitStatus;
	}

}

