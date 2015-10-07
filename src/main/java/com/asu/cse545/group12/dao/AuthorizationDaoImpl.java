package com.asu.cse545.group12.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactory.*;
import org.hibernate.Transaction;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

public class AuthorizationDaoImpl {

	@Autowired
	SessionFactory sessionfactory;
	
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
