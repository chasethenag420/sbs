package com.asu.cse545.group12.services;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
	@Autowired
	AccountDao accountdao;

	@Autowired
	UserDao userDao;

	@Override
	public int insertRow(int userId) {

		//create checking
		Account checkingAccount = new Account();
		checkingAccount.setAccountType("checking");
		checkingAccount.setActiveFlag(1);
		checkingAccount.setBalance(0);
		checkingAccount.setCreationDate(Calendar.getInstance().getTime());
		checkingAccount.setModifiedTimeStamp(Calendar.getInstance().getTime());
		checkingAccount.setUserId(userId);
		accountdao.insertRow(checkingAccount);
		//create saving
		Account savingAccount = new Account();
		savingAccount.setAccountType("saving");
		savingAccount.setActiveFlag(1);
		savingAccount.setBalance(0);
		savingAccount.setCreationDate(Calendar.getInstance().getTime());
		savingAccount.setModifiedTimeStamp(Calendar.getInstance().getTime());
		savingAccount.setUserId(userId);
		return accountdao.insertRow(savingAccount);
	}

	@Override
	public Account getAccount(String username){
		Users user=userDao.getUserByUserName(username);
		if(logger.isDebugEnabled()){
			logger.debug("**********************************User inside Accountserviceimpl: "+user.toString());
		}
		if (user==null){
			return null;
		} else{
			
			return accountdao.getAccountByUserId(user.getUserId());
		}
	}

	@Override
	public Account getAccount(int accountNumber){
		return accountdao.getAccountByAccountNumber(accountNumber);
	}

	@Override
	public boolean doCredit(int accountNumber, double amount){
		if(this.isBalanceValid(accountNumber, amount, Const.CREDIT_REQUEST)){
			Account account=this.getAccount(accountNumber);
			account.setBalance(account.getBalance()+ amount);
			account.setModifiedTimeStamp(Calendar.getInstance().getTime());
			accountdao.updateRow(account);
			return true;
		}
		return  false;
	}

	@Override
	public boolean doDebit(int accountNumber, double amount){
		if(this.isBalanceValid(accountNumber, amount, Const.DEBIT_REQUEST)){
			Account account=this.getAccount(accountNumber);
			account.setBalance(account.getBalance()- amount);
			account.setModifiedTimeStamp(Calendar.getInstance().getTime());
			accountdao.updateRow(account);
			return true;
		}
		return  false;
	}

	@Override
	public boolean isBalanceValid(int accountNumber, double amount, String type){
		Account account=getAccount(accountNumber);
		double balance=account.getBalance();
		if(Const.CREDIT_REQUEST.equals(type) && (balance+amount) >=0){
			return true;
		}else if(Const.DEBIT_REQUEST.equals(type) && (balance-amount)>=0){
			return true;
		}
		return false;
	}
	@Override
	public boolean isValidAccountNumber(int accountNumber){
		if(this.getAccount(accountNumber)!=null)
			return true;
		else 
			return false;
	}
	@Override
	public boolean isAccountNumberBelongToUser(int accountNumber, String userName){
		if(this.isValidAccountNumber(accountNumber)== false){
			return false;
		}else{
			Account account=this.getAccount(userName);
			if(account!=null && account.getAccountNumber()== accountNumber){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public Account getRowById(int accountNumber) {
		return this.getAccount(accountNumber);
	}

	@Override
	public int updateRow(Account account) {
		return accountdao.updateRow(account);
	}
	
	@Override
	public List<Account> getAccounts(int userId) {
		// TODO Auto-generated method stub
		return accountdao.getAccountsByUserId(userId);
	}
}
