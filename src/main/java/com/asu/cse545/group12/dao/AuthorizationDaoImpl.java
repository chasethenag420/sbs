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
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

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
		//authorization.setApprovalFlag("yes");
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
		//authorization.setApprovalFlag("no");
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
		//authorization.set("yes");
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
		String whereClause = "from authorization where request_status='pending' or request_status= 'forward'";
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		
		pendingEntries = query.list();
		
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		
		return pendingEntries;
		
	}
	
	@Override
	public Authorization getRowById(int authorizationId) {
		// TODO Auto-generated method stub
			Session session = sessionfactory.openSession();
			Authorization authorization= session.load(Authorization.class, authorizationId);
			return authorization;
		
	}
	
	
	@Override
	public Authorization getAuthorizationByAuthorizationId(int authorizationId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from authorization where authorizationid =:authorizationId ");
		query.setParameter("authorizationId", authorizationId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by authorizationId: "+authorizationId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by authorizationId: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Authorization)results.get(0);
		} else {
			return null;
		}
	}
	//public int deleteRow(int AccountId);

	@Override
	public Authorization getAuthorizationByTransactionId(int transactionId) {
		
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from authorization where TRANSACTION_ID =:transactionId ");
		query.setParameter("transactionId", transactionId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by transactionId: "+transactionId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Authorization by transactionId: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Authorization)results.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	@Transactional
	public int updateRow(Authorization authorization) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(authorization);
		tx.commit();
		return authorization.getAuthorizationId();
	}

	@Override
	public List<Authorization> getAuthorizednotifications(int fromuser,
			int touser) {
		Session session = sessionfactory.openSession(); 
		String status="APPROVED";
		String reqType= "view profile";
		Query query= session.createQuery("from authorization where AUTHORIZED_BY_USERID=:touser and AUTHORIZED_TO_USERID=:fromuser  and REQUEST_TYPE=:reqType");
		query.setParameter("fromuser", fromuser);
		query.setParameter("touser", touser);
		//query.setParameter("status", status);
		query.setParameter("reqType", reqType);
		List<Authorization> authlist = query.list();
		System.out.println(authlist);
		return authlist;
	}
}
