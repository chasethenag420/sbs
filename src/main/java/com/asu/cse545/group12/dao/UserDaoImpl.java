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

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Users;

@Component("UserDaoImpl")
public class UserDaoImpl implements UserDao {
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	@Transactional
	public int insertRow(Users user) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		session.saveOrUpdate(user);  
		tx.commit();  
		Serializable id = session.getIdentifier(user);  
		session.close();  
		return (Integer) id; 
	}
	@Override
	public Users getRowById(int id) {
		// TODO Auto-generated method stub
			Session session = sessionfactory.openSession();
			Users user= session.load(Users.class, id);
			return user;
		
	}

	@Override
	@Transactional
	public int updateRow(Users user) {
		// TODO Auto-generated method stub
				Session session = sessionfactory.openSession(); 
				Transaction tx = session.beginTransaction();
				session.saveOrUpdate(user);
				tx.commit();
				session.close();
				return user.getUserId();
	}
	@Override
	public Users getUserByUserName(String username){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from user where username = :username ");
		query.setParameter("username", username);
		List results = query.list();
		if(logger.isDebugEnabled()){
			logger.debug("User by username: "+username);
		}
		if(logger.isDebugEnabled()){
			logger.debug("User by username: "+results);
		}
		session.close();
		if(results.size()==1){
			return (Users)results.get(0);
		} else {
			return null;
		}
	}
	@Override
	public Users getUserByUserId(int userId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from user where userid = :userId ");
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
			return (Users)results.get(0);
		} else {
			return null;
		}
	}
	@Override
	public List<Users> getUsersByFirstName(String firstName){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from user where firstName = :firstName ");
		query.setParameter("firstName", firstName);
		List<Users> results = query.list();
		session.close();
		return results;
	}
	@Override
	public List<Users> getUsersByLastName(String lastName){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from user where lastName = :lastName ");
		query.setParameter("lastName", lastName);
		List<Users> results = query.list();
		session.close();
		return results;
	}
	@Override
	public List<Users> getUsersByPhoneNumber(String phoneNumber){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from user where phoneNumber = :phoneNumber ");
		query.setParameter("phoneNumber", phoneNumber);
		List<Users> results = query.list();
		session.close();
		return results;
	}
	@Override
	public List<Users> getUsersByEmailId(String emailId){
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from user where emailId = :emailId ");
		query.setParameter("emailId", emailId);
		List<Users> results = query.list();
		session.close();
		return results;
	}
		

}