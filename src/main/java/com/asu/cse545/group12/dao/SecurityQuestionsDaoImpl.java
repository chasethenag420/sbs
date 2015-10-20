package com.asu.cse545.group12.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.domain.SecurityQuestions;
import com.asu.cse545.group12.domain.Users;

@Component("SecurityQuestionsDaoImpl")
public class SecurityQuestionsDaoImpl implements SecurityQuestionsDao {
	private static final Logger logger = Logger.getLogger(SecurityQuestionsDaoImpl.class);
	
	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	@Transactional
	public int insertRow(SecurityQuestions securityQuestions) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		session.saveOrUpdate(securityQuestions);  
		tx.commit();  
		Serializable id = session.getIdentifier(securityQuestions);  
		session.close();  
		return (Integer) id; 
	}
	
	
	@Override
	public SecurityQuestions getRowById(int id) {
		// TODO Auto-generated method stub
			Session session = sessionfactory.openSession();
			SecurityQuestions securityQuestions= session.load(SecurityQuestions.class, id);
			return securityQuestions;
		
	}

	@Override
	@Transactional
	public int updateRow(SecurityQuestions securityQuestions) {
		// TODO Auto-generated method stub
				Session session = sessionfactory.openSession(); 
				Transaction tx = session.beginTransaction();
				session.saveOrUpdate(securityQuestions);
				tx.commit();
				session.close();
				return securityQuestions.getId();
	}
	
	
	@Override
	public SecurityQuestions getSecurityQuestionsByUserId(int userId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from securityquestions where userid = :userId ");
		query.setParameter("userId", userId);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("User by userId: "+userId);
		}
		if(logger.isDebugEnabled()){
			logger.debug("User by userId: "+results);
		}
		session.close();
		if(results.size()==1){
			return (SecurityQuestions)results.get(0);
		} else {
			return null;
		}
	}

	
		

}