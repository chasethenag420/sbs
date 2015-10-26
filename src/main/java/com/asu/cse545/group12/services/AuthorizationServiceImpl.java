package com.asu.cse545.group12.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.AccessControlDao;
import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.RoleDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.TransferDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.AccessControl;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Transfer;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.pki.CertificateGeneration;

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

	@Autowired
	AccessControlDao accessControlDao;

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
			CertificateGeneration certGen = new CertificateGeneration();
			String attachments[] = certGen.certificateGeneration(userName);
			certGen.sendNotificationEmail(userName, attachments);
			// create an account for this user by default checkings
			accountService.insertRow(requestor.getUserId());

			//add access control entry for this user
			AccessControl accessControl = new AccessControl();
			accessControl.setUserId(requestor.getUserId());
			//set all possible operations on this user allowed for only manager
			accessControl.setCancel_transaction(4);
			accessControl.setDelete_external_profile(4);
			accessControl.setModify_external_profile(4);
			accessControl.setModify_transaction(4);
			accessControl.setView_external_profile(4);
			accessControl.setView_transaction(4);
			accessControlDao.insertRow(accessControl);
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
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());

			Account account = accountService.getAccount(transaction.getAccountNumber());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************authorization: " + authorization.toString());
				logger.debug("**********************************Account: " + account.toString());
			}

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

			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());

			Account account = accountService.getAccount(transaction.getAccountNumber());
			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}

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

			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());

			Account account = accountService.getAccount(transaction.getAccountNumber());

			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}

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
		else if (Const.VIEW_TRANSACTION_REQUEST.equals(authorization.getRequestType()) || Const.MODIFY_TRANSACTIONS_REQUEST.equals(authorization.getRequestType()) || Const.CANCEL_TRANSACTION_REQUEST.equals(authorization.getRequestType()) || Const.VIEW_PROFILE.equals(authorization.getRequestType()) || Const.UPDATE_USERINFO_REQUEST.equals(authorization.getRequestType()) || Const.DELETE_EXTERNAL_REQUEST.equals(authorization.getRequestType()))
		{
			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.APPROVED);
			authorization.setAssignedToRole(approver.getRoleId());
			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/
			int approvedToUserId = authorization.getAuthorizedToUserId();
			Users requester = userDao.getUserByUserId(approvedToUserId);
			if(requester.getRoleId() == 3)
			{
				//retrieve the access control of approver external user
				AccessControl accessControl = accessControlDao.getAccessControlByUserId(approver.getUserId());
				if(Const.VIEW_TRANSACTION_REQUEST.equals(authorization.getRequestType()))
				{
					//set view transaction to 3 i.e. regular user now on can view transactions of this external user
					accessControl.setView_transaction(3);
				}
				else if(Const.MODIFY_TRANSACTIONS_REQUEST.equals(authorization.getRequestType()))
				{
					//set modify transaction to 3 i.e. regular user now on can modify transactions of this external user
					accessControl.setModify_transaction(3);
				}
				else if(Const.CANCEL_TRANSACTION_REQUEST.equals(authorization.getRequestType()))
				{
					//set cancel transaction to 3 i.e. regular user now on can cancel transactions of this external user
					accessControl.setCancel_transaction(3);;
				}
				else if(Const.VIEW_PROFILE.equals(authorization.getRequestType()))
				{
					//set view profile to 3 i.e. regular user now on can view profile of this external user
					accessControl.setView_external_profile(3);;
				}
				else if(Const.UPDATE_USERINFO_REQUEST.equals(authorization.getRequestType()))
				{
					//set update profile to 3 i.e. regular user now on can update profile of this external user
					accessControl.setModify_external_profile(3);
				}
				else if(Const.DELETE_EXTERNAL_REQUEST.equals(authorization.getRequestType()))
				{
					//set view transaction to 3 i.e. regular user now on can view transactions of this external user
					accessControl.setDelete_external_profile(3);
				}
				accessControlDao.updateRow(accessControl);
			}

		}
		//return authorizationDao.approve(authorization);
		return 0;
	}

	@Override
	public List<Authorization> getNotifications(Users user) {
		// TODO Auto-generated method stub 
		logger.debug("INSIDE THE GET NOTIF WITH USER:" + user.getRoleId());
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

			//COMMENTED THIS LINE TO STOP UPDATING THE ACCOUNT WITH CREDIT AMOUNT SINCE THE REQ IS REJECTED
			//accountService.doCredit(account.getAccountNumber(), transaction.getAmount());
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

			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());

			//COMMENTED THIS LINE TO STOP UPDATING THE ACCOUNT WITH CREDIT AMOUNT SINCE THE REQ IS REJECTED
			//accountService.doDebit(account.getAccountNumber(), transaction.getAmount());
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

			if (logger.isDebugEnabled()) {
				logger.debug("**********************************TransactionId: " + authorization.getTransactionId());
			}
			Transactions transaction = transactionDao.getTransactionByTransactionId(authorization.getTransactionId());

			//COMMENTED THIS LINE TO STOP UPDATING THE ACCOUNT WITH CREDIT AMOUNT SINCE THE REQ IS REJECTED
			//accountService.doDebit(account.getAccountNumber(), transaction.getAmount());

			transaction.setTransactionStatus(Const.REJECT);
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setModifiedByUserid(approver.getUserId());
			transactionDao.updateRow(transaction);

			// for credit
			Transfer transfer = transferDao.getTransferByTransferId(transaction.getTransferId());
			if (transfer != null) {
				Transactions creditTransaction = transactionDao.getTransactionByTransactionId(transfer.getUserToTransactionid());
				if (logger.isDebugEnabled()) {
					logger.debug(
							"**********************************creditTransactionId: " + creditTransaction.getTransactionId());
				}

				//COMMENTED THIS LINE TO STOP UPDATING THE ACCOUNT WITH CREDIT AMOUNT SINCE THE REQ IS REJECTED
				//accountService.doCredit(creditAccount.getAccountNumber(), creditTransaction.getAmount());

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

	@Override
	public Authorization getAuthorizationByAuthorizationId(int authorizationId)
	{
		return authorizationDao.getAuthorizationByAuthorizationId(authorizationId);
	}

	@Override
	public int approveTransferByMerchant(int authorizationId, String userName)
	{

		// TODO Auto-generated method stub
		Authorization authorization = authorizationDao.getAuthorizationByAuthorizationId(authorizationId);
		// based on type of transaction trigger relevant action
		if (Const.TRANSFER_REQUEST.equals(authorization.getRequestType())) {


			Users approver = userDao.getUserByUserName(userName);
			authorization.setAuthorizedByUserId(approver.getUserId());
			authorization.setRequestStatus(Const.APPROVED);
			authorization.setAssignedToRole(approver.getRoleId());


			//create new authorization which will go to the manager
			Authorization newAuthorization = new Authorization();
			newAuthorization.setAuthorizedToUserId(approver.getUserId());
			newAuthorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			newAuthorization.setRequestDescription(authorization.getRequestDescription()+"\n Merchant payment from customer approved by merchant "+approver.getFirstName()+" "+approver.getLastName());
			newAuthorization.setRequestType(Const.TRANSFER_REQUEST);
			newAuthorization.setRequestStatus(Const.PENDING);

			//SETTNG THE ROLE ID TO Manager USER 
			int roleid = roleDao.getRoleid(Const.MANAGER);
			newAuthorization.setAssignedToRole(roleid);

			newAuthorization.setTransactionId(authorization.getTransactionId());
			authorizationDao.insertRow(newAuthorization);


			//*************************************************************************************************************/
			//ADDED THIS CODE TO MAKE THE ABOVE UPDATES IN THE authorization object reflect in the database 
			authorizationDao.updateRow(authorization);
			//*************************************************************************************************************/

			return 1;
		}
		//return authorizationDao.approve(authorization);


		return -1;
	}

	@Override
	public List<Authorization> getApprovedPendingNotifications(Users user)
	{

		// TODO Auto-generated method stub 
		logger.debug("INSIDE THE GET NOTIF WITH USER:" + user.getRoleId());
		if("1".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getApprovedPendingNotificationsForExternal(user);
		}
		else if("2".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getApprovedPendingNotificationsForMerchant(user);
		}
		else if("3".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getApprovedPendingNotificationsForRegular(user);
		}
		else if("4".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getApprovedPendingNotificationsForManager(user);
		}
		else if("5".equals(user.getRoleId().toString()))
		{
			return authorizationDao.getApprovedPendingNotificationsForAdmin(user);
		}

		return null;


	}

	public List<AccessControl> getAccessControlToView(int touser, int roleID )
	{
		return authorizationDao.getAccessControlToView(touser, roleID);
	}

	public List<AccessControl> getAccessControlToModify(int touser, int roleID ) 
	{
		return authorizationDao.getAccessControlToModify(touser, roleID);
	}

	public List<AccessControl> getAccessControlToDelete(int touser, int roleID )
	{
		return authorizationDao.getAccessControlToDelete(touser, roleID);
	}
	public List<AccessControl> getAccessControlToViewTransaction(int touser, int roleID ) {
		return authorizationDao.getAccessControlToViewTransaction(touser, roleID);
	}

	public List<AccessControl> getAccessControlToModifyTransaction(int touser, int roleID ) {
		return authorizationDao.getAccessControlToModifyTransaction(touser, roleID);
	}

	public List<AccessControl> getAccessControlToDeleteTransaction(int touser, int roleID ) {
		return authorizationDao.getAccessControlToDeleteTransaction(touser, roleID);
	}
	public Authorization getAuthorizationByTransactionId(int transactionId){
		return authorizationDao.getAuthorizationByTransactionId(transactionId);
	}
}
