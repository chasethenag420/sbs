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

//	@Override
//	public List<Authorization> getNotifications() 
//	{
//		String whereClause = "from authorization where request_status='pending' or request_status= 'forward'";
//		List<Authorization> pendingEntries = new ArrayList<Authorization>();
//		Session session = sessionfactory.openSession(); 
//		Query query = session.createQuery(whereClause);
//		
//		pendingEntries = query.list();
//		
//		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
//		
//		return pendingEntries;
//		
//	}
	
	@Override
	public List<Authorization> getNotificationsForExternal(Users user) 
	{
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED ---- ALL THE REQUESTS RAISED BY HIM ONLY --- PENDING,FORWARDED AND APPROVED ONES
		String whereClause = "from authorization where authorized_to_userid='"+ user.getUserId()+"' or authorized_by_userid='"+ user.getUserId()+"'";
		System.out.println("Ext User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getNotificationsForMerchant(Users user) 
	{
		//CAN SEE ONLY THE REQUESTS WHICH HE RAISED ---- ALL THE REQUESTS RAISED BY HIM ONLY --- PENDING,FORWARDED AND APPROVED ONES 
		String whereClause = "from authorization where authorized_to_userid='"+ user.getUserId()+"' or authorized_by_userid='"+ user.getUserId()+"'";
		System.out.println("Merchant Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	@Override
	public List<Authorization> getNotificationsForRegular(Integer roleid)
	{
		//WE CAN DEFINE THE TYPE OF REQUESTS THAT CAN BE SEEN BY A REGULAR USER ... AS OF NOW I DEFINED THAT HE CANNOT SEE FORWARDED REQUESTS AND SIGNUP REQUESTS
		String whereClause = "from authorization where request_status='pending' and request_type not like('Signup'))";
		System.out.println("Regular User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}

	public List<Authorization> getNotificationsForManager(Integer roleid)
	{
		//WE CAN DEFINE THE TYPE OF REQUESTS THAT CAN BE SEEN BY A REGULAR USER ... AS OF NOW I DEFINED THAT HE CAN ALL TYPES OF REQ BUT CANNOT SEE "PII ACCESS" REQUESTED BY GOVT AGENCIES -- ONLY ADMIN CAN SEE THAT REQUESTS
		String whereClause = "from authorization where request_status like 'Pending' or request_status like 'forward' or request_status like 'approve' or request_status like 'reject' or request_status like 'inactive' and request_type not like('PII Access'))";
		System.out.println("Manager User Notif Where clause:" + whereClause);
		List<Authorization> pendingEntries = new ArrayList<Authorization>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		pendingEntries = query.list();
		logger.debug("PENDING ENTRIES SIZE IS:" + pendingEntries.size());
		return pendingEntries;
	}
	
	public List<Authorization> getNotificationsForAdmin(Integer roleid)
	{
		//WE CAN DEFINE THE TYPE OF REQUESTS THAT CAN BE SEEN BY A REGULAR USER ... AS OF NOW HE CAN SEE PENDING AND FORWARDED, BUT CANNOT SEE COMPLETED OR REJECTED REQUESTS, AND ALSO SEES "PII ACCESS" REQUESTED BY GOVT AGENCIES
		String whereClause = "from authorization where request_status='pending' or request_status= 'forward' or request_type like('PII Access'))";
		System.out.println("Admin User Notif Where clause:" + whereClause);
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
