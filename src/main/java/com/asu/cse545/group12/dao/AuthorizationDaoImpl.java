package com.asu.cse545.group12.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.domain.Authorization;

@Component
public class AuthorizationDaoImpl implements AuthorizationDao {

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
		//*************************88**************************************************
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
		authorization.setApprovalFlag("Yes");
		authorization.setRequestStatus("Approve");
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
		authorization.setApprovalFlag("No");
		authorization.setRequestStatus("Reject");
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
		authorization.setApprovalFlag("Yes");
		authorization.setRequestStatus("Forward");
		session.saveOrUpdate(authorization);  
		tx.commit();  
		Serializable authid = session.getIdentifier(authorization);  
		session.close();  
		return (Integer) authid;		
	}

	@Override
	public List<Authorization> getNotifications() 
	{
		String fromClause = "FROM Authorization";
		List<Authorization> unfinishedEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(fromClause);
		unfinishedEntries = query.list();
		
		return unfinishedEntries;
		
	}
	//public int deleteRow(int AccountId);
}
