package com.asu.cse545.group12.services;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.RoleDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.TransactionDaoImpl;
import com.asu.cse545.group12.dao.TransferDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Transfer;
import com.asu.cse545.group12.domain.Users;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private static final Logger logger = Logger.getLogger(AuthorizationServiceImpl.class);
	@Autowired
	AuthorizationDao authorizationDao;

	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	AccountService accountService;

	@Autowired
	TransferDao transferDao;

	@Override
	public int approve(int authorizationId, String userName) {
		// TODO Auto-generated method stub
		Authorization authorization = authorizationDao.getAuthorizationByAuthorizationId(authorizationId);
		// based on type of transaction trigger relevant action
		if (Const.SIGNUP_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.APPROVED);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database
			if (logger.isDebugEnabled()) {
				logger.debug("****************KKKKKKKKKKKKK***************:" );
			}
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/
			Users requestor = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			requestor.setUserStatus(Const.ACTIVE);
			userDao.updateRow(requestor);
			// create an account for this user by default checkings
			accountService.insertRow(requestor.getUserId());
		} else if (Const.CREDIT_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.APPROVED);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/
			Users requestor = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************requestor: " + requestor.toString());
			}
			Account account = accountService.getAccount(requestor.getUserName());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************authorization: " + authorization.toString());
				logger.debug("**********************************Account: " + account.toString());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());
			accountService.doCredit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus(Const.APPROVED);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);
		}

		else if (Const.DEBIT_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.APPROVED);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			Users requestor = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			Account account = accountService.getAccount(requestor.getUserName());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());
			accountService.doDebit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus(Const.APPROVED);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);
		}
		else if (Const.TRANSFER_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.APPROVED);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			// for debit
			Users requestor1 = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			Account account = accountService.getAccount(requestor1.getUserName());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());
			accountService.doDebit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus(Const.APPROVED);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);

			// for credit
			Transfer transfer = transferDao.getTransferByTransferId(transaction.getTransferId());
			if (transfer != null) {
				Transactions creditTransaction = transactionDao.getTransactionByTransactionId(transfer.getUserToTransactionid());
				Account creditAccount = accountService.getAccount(creditTransaction.getAccountNumber());
				if (logger.isDebugEnabled()) {
					logger.debug(
							"**********************************creditTransactionId: " + creditTransaction.getTransactionId());
				}
				
				accountService.doCredit(creditAccount.getAccountNumber(), creditTransaction.getAmount());
				creditTransaction.setTransactionStatus(Const.APPROVED);
				creditTransaction.setModifiedTimestamp(Calendar.getInstance().getTime());
				creditTransaction.setModifiedByUserid(approver.getUserId());
				transactionDao.updateRow(creditTransaction);
			}

		}
		//return authorizationDao.approve(authorization);
		return 0;
	}

	@Override
	public List<Authorization> getNotifications(Users user) {
		// TODO Auto-generated method stub 
		if("1".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getNotificationsForExternal(user);
		}
		else if("2".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getNotificationsForMerchant(user);
		}
		else if("3".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getNotificationsForRegular(user);
		}
		else if("4".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getNotificationsForManager(user);
		}
		else if("5".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getNotificationsForAdmin(user);
		}
		
		return null;

//		return authorizationDao.getNotifications();
	}
	
	@Override
	public int reject(int authorizationId, String userName) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("Reject notification invoked");
		}
		Authorization authorization = authorizationDao.getAuthorizationByAuthorizationId(authorizationId);
		// based on type of transaction trigger relavent action
		if (Const.SIGNUP_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.REJECT);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			Users requestor = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			requestor.setUserStatus(Const.INACTIVE);
			userDao.updateRow(requestor);
		} else if (Const.CREDIT_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.REJECT);			
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			Users requestor = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************requestor: " + requestor.toString());
			}
			Account account = accountService.getAccount(requestor.getUserName());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************authorization: " + authorization.toString());
				logger.debug("**********************************Account: " + account.toString());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());
			accountService.doCredit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus(Const.REJECT);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);
		}
		else if (Const.DEBIT_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.REJECT);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			Users requestor = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			Account account = accountService.getAccount(requestor.getUserName());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());
			accountService.doDebit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus(Const.REJECT);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);
		}
		else if (Const.TRANSFER_REQUEST.equals(authorization.getRequestType())) {
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.REJECT);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			// for debit
			Users requestor1 = userDao.getUserByUserId(authorization.getAuthorizedToUserId());
			Account account = accountService.getAccount(requestor1.getUserName());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());
			accountService.doDebit(account.getAccountNumber(), transaction.getAmount());
			transaction.setTransactionStatus(Const.REJECT);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);

			// for credit
			Transfer transfer = transferDao.getTransferByTransferId(transaction.getTransferId());
			if (transfer != null) {
				Transactions creditTransaction = transactionDao.getTransactionByTransactionId(transfer.getUserToTransactionid());
				Account creditAccount = accountService.getAccount(creditTransaction.getAccountNumber());
				if (logger.isDebugEnabled()) {
					logger.debug(
							"**********************************creditTransactionId: " + creditTransaction.getTransactionId());
				}
				
				accountService.doCredit(creditAccount.getAccountNumber(), creditTransaction.getAmount());
				creditTransaction.setTransactionStatus(Const.REJECT);
				creditTransaction.setModifiedTimestamp(Calendar.getInstance().getTime());
				creditTransaction.setModifiedByUserid(approver.getUserId());
				transactionDao.updateRow(creditTransaction);
			}
		}
		//return authorizationDao.reject(authorization);
		return 0;
	}

	@Override
	public int forward (int authorizationId, String userName){
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("Forward notification invoked");
		}
		Users forwarder = userDao.getUserByUserName(userName);
		Authorization authorization = authorizationDao.getAuthorizationByAuthorizationId(authorizationId);
		//IF THE ROLE IS 3 THEN IT IS FORWARDED TO ROLE 4
		if(forwarder.getRoleId()==3)
		{
			authorization.setAssignedToRole(forwarder.getRoleId()+1);

		}
		else
		{
			authorization.setAssignedToRole(forwarder.getRoleId());
		}
		
		authorization.setRequestStatus(Const.FORWARD);
		//WE CAN PUT THE HEADER IN THE JSP AS "APPROVER ID/FORWARDER ID"
		authorization.setAuthorizedByUserId(forwarder.getUserId());
		
		return authorizationDao.updateRow(authorization);
		
		//return 0;
		//authorizationDao.forward(authorization);
	}

	@Override
	public int signupInsertRow(Users user) {
		Authorization authorizationRequest = new Authorization();
		authorizationRequest.setAuthorizedToUserId(user.getUserId());
		authorizationRequest.setRequestStatus(Const.PENDING);
		authorizationRequest.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
		authorizationRequest.setRequestDescription("Approval for account creation");
		authorizationRequest.setRequestType(Const.SIGNUP_REQUEST);

		//SETTNG THE ROLE ID TO MANAGER 
		int roleid = 4;//roleDao.getRoleid(Const.MANAGER);
		authorizationRequest.setAssignedToRole(roleid);

		return authorizationDao.insertRow(authorizationRequest);
	}


	@Override
	public int regularEmpRequest(Authorization authorization) {
		// TODO Auto-generated method stub
		authorizationDao.insertRow(authorization);
		return 0;
	}

	@Override
	public List<Authorization> getAuthorizedNotifications(int fromuser,
			int touser, String reqType, String requestStatus) {
		List<Authorization> authorizationList =authorizationDao.getAuthorizednotifications(fromuser,touser,reqType,requestStatus);
		return authorizationList;
	}

	@Override
	public int update(Authorization authorization) {
		authorizationDao.updateRow(authorization);
		return 0;
	}
	
}
