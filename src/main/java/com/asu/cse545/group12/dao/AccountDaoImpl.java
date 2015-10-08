package com.asu.cse545.group12.dao;

import java.io.Serializable;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.domain.Account;


public class AccountDaoImpl implements AccountDao{
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
	public Account getRowById(int AccountId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Account account =session.load(Account.class, AccountId);
		return account;
	}

	@Override
	public int updateRow(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

}
