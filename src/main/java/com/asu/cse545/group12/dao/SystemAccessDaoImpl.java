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

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.SecurityQuestions;
import com.asu.cse545.group12.domain.SystemAccess;
import com.asu.cse545.group12.domain.Users;

@Component("SystemAccessDaoImpl")
public class SystemAccessDaoImpl  implements SystemAccessDao{
	private static final Logger logger = Logger.getLogger(SystemAccessDaoImpl.class);

	@Autowired
	SessionFactory sessionfactory;

	@Override
	public SystemAccess getRowById(int id){
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		SystemAccess systemAccess= session.load(SystemAccess.class, id);
		return systemAccess;
	}
	@Override
	@Transactional
	public int insertRow(SystemAccess systemAccess){
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		session.saveOrUpdate(systemAccess);  
		tx.commit();  
		Serializable id = session.getIdentifier(systemAccess);  
		session.close();  
		return (Integer) id; 
	}
	@Override
	public int updateRow(SystemAccess systemAccess){

		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(systemAccess);
		tx.commit();
		session.close();
		return systemAccess.getId();

	}
	@Override
	public List<SystemAccess> getAllSystemAccess(){

		String whereClause = "from systemaccess where 1=1)";
		List<SystemAccess> pendingEntries = new ArrayList<SystemAccess>();
		Session session = sessionfactory.openSession(); 
		Query query = session.createQuery(whereClause);
		//query.setParameter("role", role);
		pendingEntries = query.list();
		return pendingEntries;
	}

}
