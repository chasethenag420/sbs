package com.asu.cse545.group12.dao;



import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.domain.Transfer;

public class TransferDaoImpl implements TransferDao {

	
	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public int insertRow(Transfer transfer) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(transfer);
		tx.commit();
		Serializable transferId = session.getIdentifier(transfer);  
		session.close();  
		return (Integer) transferId;
		
	
	}

	@Override
	public Transfer getRowById(int transferId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transfer transfer=session.load(Transfer.class, transferId);
		return transfer;
	}

	@Override
	public int updateRow(Transfer transfer) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(transfer);
		tx.commit();
		Serializable transferId = session.getIdentifier(transfer); 
		session.close();
		return (int) transferId;
	}

}
