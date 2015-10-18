package com.asu.cse545.group12.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.hashing.HashGenerator;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.TransferDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Transfer;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.email.EmailSenderAPI;

@Service("TransactionsServiceImpl")
public class TransactionsServiceImpl implements TransactionsService {
	private static final Logger logger = Logger.getLogger(TransactionsServiceImpl.class);

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	AuthorizationDao authorizationDao;

	@Autowired
	TransferDao transferDao;
	
	@Autowired
	UserDao userDao;

	public int doCredit(int accountNumber, int amount) {
		boolean creditStatus = accountService.isBalanceValid(accountNumber, amount, "credit");
		if (creditStatus == true) {
			Account account = accountService.getAccount(accountNumber);
			Transactions transaction = new Transactions();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(amount);
			transaction.setCreationTimestamp(Calendar.getInstance().getTime());
			transaction.setTransactionStatus("submitted");
			transaction.setUserId(account.getUserId());
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setSeverity("critical");
			transaction.setTransactionType("credit");
			int transactionId = transactionDao.insertRow(transaction);

			Authorization authorization = new Authorization();
			authorization.setAuthorizedToUserId(account.getUserId());
			authorization.setRequestStatus("submitted");
			authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			authorization.setRequestDescription("Approval for amount credit");
			authorization.setRequestType("Credit");
			authorization.setTransactionId(transactionId);
			authorizationDao.insertRow(authorization);

			return transactionId;
		}
		return 0;
	}

	public int doDebit(int accountNumber, int amount) {
		boolean debitStatus = accountService.isBalanceValid(accountNumber, amount, "debit");
		if (debitStatus == true) {
			Account account = accountService.getAccount(accountNumber);
			Transactions transaction = new Transactions();
			transaction.setAccountNumber(accountNumber);
			transaction.setAmount(amount);
			transaction.setCreationTimestamp(Calendar.getInstance().getTime());
			transaction.setTransactionStatus("submitted");
			transaction.setUserId(account.getUserId());
			transaction.setModifiedTimestamp(Calendar.getInstance().getTime());
			transaction.setSeverity("critical");
			transaction.setTransactionType("debit");
			int transactionId = transactionDao.insertRow(transaction);

			Authorization authorization = new Authorization();
			authorization.setAuthorizedToUserId(account.getUserId());
			authorization.setRequestStatus("submitted");
			authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			authorization.setRequestDescription("Approval for amount debit");
			authorization.setRequestType("Debit");
			authorization.setTransactionId(transactionId);
			authorizationDao.insertRow(authorization);

			return transactionId;
		}
		return 0;
	}


	@Override
	public List<Transactions> searchTransactionByInternals(int accountNumber) {
		List<Transactions> searchTransactions=transactionDao.getTransactionsByAccNum(accountNumber);
		return searchTransactions;
	}



	@Override
	public List<Transactions> searchTransactionByExternals(Integer accountNum,Date toDate,
			Date fromDate) {
		
		List<Transactions> transactionlist =transactionDao.getTransactionsByDate(accountNum, toDate, fromDate);
		return transactionlist;
	}

	




	public int doTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
		boolean debitStatus = false;
		if (accountService.isValidAccountNumber(toAccountNumber)
				&& accountService.isValidAccountNumber(fromAccountNumber)) {
			debitStatus = accountService.isBalanceValid(fromAccountNumber, amount, "debit");
			if (debitStatus == true) {
				Account fromAccount = accountService.getAccount(fromAccountNumber);
				Account toAccount = accountService.getAccount(toAccountNumber);

				// create transaction for debit
				Transactions debitTransaction = new Transactions();
				debitTransaction.setAccountNumber(fromAccountNumber);
				debitTransaction.setAmount(amount);
				debitTransaction.setCreationTimestamp(Calendar.getInstance().getTime());
				if (amount > 1000) {
					debitTransaction.setTransactionStatus("submitted");
					debitTransaction.setSeverity("critical");
				} else {
					debitTransaction.setTransactionStatus("complete");
					debitTransaction.setSeverity("non-critical");
					accountService.doDebit(fromAccountNumber, amount);
				}
				debitTransaction.setUserId(fromAccount.getUserId());
				debitTransaction.setModifiedTimestamp(Calendar.getInstance().getTime());
				debitTransaction.setTransactionType("debit");
				int debitTransactionId = transactionDao.insertRow(debitTransaction);


				// create transaction for credit
				Transactions creditTransaction = new Transactions();
				creditTransaction.setAccountNumber(toAccountNumber);
				creditTransaction.setAmount(amount);
				creditTransaction.setCreationTimestamp(Calendar.getInstance().getTime());
				if (amount > 1000) {
					creditTransaction.setSeverity("critical");
					creditTransaction.setTransactionStatus("submitted");
				} else {
					creditTransaction.setSeverity("non-critical");
					creditTransaction.setTransactionStatus("complete");
					accountService.doCredit(toAccountNumber, amount);
				}
				creditTransaction.setUserId(toAccount.getUserId());
				creditTransaction.setModifiedTimestamp(Calendar.getInstance().getTime());
				creditTransaction.setTransactionType("credit");
				int creditTransactionId = transactionDao.insertRow(creditTransaction);

				// create transfer
				Transfer transfer = new Transfer();
				if (amount > 1000) {
					transfer.setTransactionStatus("submitted");
				} else {
					transfer.setTransactionStatus("complete");
				}
				transfer.setUserFromTransactionid(debitTransactionId);
				transfer.setUserToTransactionid(creditTransactionId);
				int transferId = transferDao.insertRow(transfer);

				// set transferId in both transactions
				debitTransaction.setTransferId(transferId);
				creditTransaction.setTransferId(transferId);
				transactionDao.updateRow(debitTransaction);
				transactionDao.updateRow(creditTransaction);

				// create authorization for amount >1000. authorization has only
				// fromAccount information
				if (amount > 1000) {
					Authorization authorization = new Authorization();
					authorization.setAuthorizedToUserId(fromAccount.getUserId());
					authorization.setRequestStatus("submitted");
					authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
					authorization.setRequestDescription("Approval for amount transfer");
					authorization.setRequestType("Transfer");
					authorization.setTransactionId(debitTransactionId);
					authorizationDao.insertRow(authorization);
				}
				return debitTransactionId;
			}
		}
		return 0;
	}

	//sent the OTP to user email
	@Override
	public void sendOTPviaEmail(Users user)
	{
		String OTP = generateOTP();
		user.setOTP(OTP);
		userDao.updateRow(user);
		
		String configFile = "com/asu/cse545/group12/email/mail-config.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configFile);

		// @Service("emailService") <-- same annotation you specified in crunchifyEmailAPI.java
		EmailSenderAPI emailAPI = (EmailSenderAPI) context.getBean("emailSenderService");
		String toAddr = user.getEmailId();

		// email subject
		String subject = "OTP for Successful Transaction";

		// email body
		String body = "Dear "+user.getFirstName()+" "+user.getLastName()+",\n\n One Time Password for your transaction. \n\n OTP: "+user.getOTP()+"\n\n You need to input the OTP in the prompt for processing transaction. \n Have a good day!";

		emailAPI.setToEmailAddress(toAddr);
		emailAPI.setBody(body);
		emailAPI.setSubject(subject);
		emailAPI.sendEmail();
	}

	//generate OTP
	public String generateOTP()
	{
		String uuid = UUID.randomUUID().toString();
		if(logger.isDebugEnabled()){
			logger.debug("New generated OTP: "+uuid.substring(0, 8));
		}
		return uuid.substring(0, 8);
	}

}
