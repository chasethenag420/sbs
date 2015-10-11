package com.asu.cse545.group12.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Users;


public class AccountDaoImpl implements AccountDao{
	private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public int insertRow(Account account) {
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(account);
		tx.commit();
		Serializable AccountId = session.getIdentifier(account);  
		session.close();  
		return (Integer) AccountId;
	}
	
	@Override
	public Account getAccountByUserId(int userId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from account where userid = :userId ");
		query.setParameter("userId", userId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Account by userid: "+userId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Account by userid: "+results);
		}
		if(results.size()>=1){
			return (Account)results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Account getRowById(int AccountId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Account account =session.load(Account.class, AccountId);
		return account;
	}

	@Override
	@Transactional
	public int updateRow(Account account) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(account);
		tx.commit();
		return account.getAccountNumber();
	}
	
	
	@Override
	public Account getAccountByAccountNumber(int accountNumber){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from account where accountNum = :accountNumber ");
		query.setParameter("accountNumber", accountNumber);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Account by AccountNumber: "+accountNumber);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Account by AccountNumberd: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Account)results.get(0);
		} else {
			return null;
		}
	}

}
