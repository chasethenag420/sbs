package com.asu.cse545.group12.dao;



import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Transfer;

public class TransferDaoImpl implements TransferDao {

	private static final Logger logger = Logger.getLogger(TransferDaoImpl.class);
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
		return (Integer) transferId;
	}
	
	@Override
	public Transfer getTransferByTransferId(int transferId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from transfer where transferId =:transferId ");
		query.setParameter("transferId", transferId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Transaction by transactionId: "+transferId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Transaction by transactionId: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Transfer)results.get(0);
		} else {
			return null;
		}
	}

}
