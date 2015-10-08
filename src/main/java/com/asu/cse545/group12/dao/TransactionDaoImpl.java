package com.asu.cse545.group12.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.domain.Transactions;

public class TransactionDaoImpl implements TransactionDao {
	
	@Autowired
	SessionFactory sessionfactory;
	
	

	@Override
	@Transactional
	public int insertRow(Transactions transaction) {
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		session.saveOrUpdate(transaction);  
		tx.commit();  
		Serializable id = session.getIdentifier(transaction);  
		session.close();  
		return (Integer) id;
	}



	@Override
	public Transactions getRowById(
			int transactionId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transactions transaction=session.load(Transactions.class,transactionId);
		return transaction ;
	}



	@Override
	public int updateRow(Transactions transaction) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(transaction);
		tx.commit();
		session.close();
		return 0;
	}

	
}