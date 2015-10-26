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

import com.asu.cse545.group12.domain.AccessControl;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

@Component
public class AccessControlDaoImpl implements AccessControlDao{
	private static final Logger logger = Logger.getLogger(AccessControlDaoImpl.class);
	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public int insertRow(AccessControl accessControl) {
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(accessControl);
		tx.commit();
		Serializable Id = session.getIdentifier(accessControl);  
		session.close();  
		return (Integer) Id;
	}
	
	@Override
	public AccessControl getAccessControlByUserId(int userId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from accesscontrol where userid = :userId ");
		query.setParameter("userId", userId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("AccessControl by userid: "+userId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("AccessControl by userid: "+results);
		}
		if(results.size()>=1){
			return (AccessControl)results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public AccessControl getRowById(int Id) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		AccessControl accesscontrol =session.load(AccessControl.class, Id);
		return accesscontrol;
	}

	@Override
	@Transactional
	public int updateRow(AccessControl accesscontrol) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(accesscontrol);
		tx.commit();
		return accesscontrol.getId();
	}
	
	
	
}
