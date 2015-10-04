package com.asu.cse545.group12.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactory.*;
import org.hibernate.Transaction;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.domain.Users;

@Component("userDaoImpl")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	@Transactional
	public int insertRow(Users user) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		user.setUserStatus("InActive");
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
				return user.getUserId();
	}
	/*@Override
	public List<Users> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int deleteRow(int id) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	
	
}