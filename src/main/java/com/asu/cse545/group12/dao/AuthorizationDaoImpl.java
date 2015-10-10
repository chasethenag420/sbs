package com.asu.cse545.group12.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.controller.LoginController;
import com.asu.cse545.group12.domain.Authorization;

@Component
public class AuthorizationDaoImpl implements AuthorizationDao {

	private static final Logger logger = Logger.getLogger(AuthorizationDaoImpl.class);
	
	@Autowired
	SessionFactory sessionfactory;
	
	
	//*************************************************************************************************
	//              IN all this methods the Authorization ID will be the input , 
	//										Update the Flags based on the Authorization ID
	//*************************************************************************************************
	
	@Override
	@Transactional
	public int insertRow(Authorization authorization) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		//*************************++**************************************************
		//      DATA NEEDED TO INSERT THE AUTHORIZATION TABLE ENTRIES
		//*****************************************************************************
		//user.setUserStatus("InActive");
		session.saveOrUpdate(authorization);  
		//session.saveOrUpdate(user.getuserpii()); 
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid; 
	}
	
	@Override
	public int approve(Authorization authorization)
	{
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		authorization.setApprovalFlag("yes");
		authorization.setRequestStatus("approve");
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}
	//public List<Users> getList();

	@Override
	public int reject(Authorization authorization)
	{
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		authorization.setApprovalFlag("no");
		authorization.setRequestStatus("reject");
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}
	
	@Override
	public int forward(Authorization authorization)
	{
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		authorization.setApprovalFlag("yes");
		authorization.setRequestStatus("forward");
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}

	@Override
	public List<Authorization> getNotifications() 
	{
		String whereClause = "FROM Authorization WHERE Request_status='pending' or Request_status= 'forward'";
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		
		pendingEntries = query.list();
		
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		
		return pendingEntries;
		
	}
	//public int deleteRow(int AccountId);
}
