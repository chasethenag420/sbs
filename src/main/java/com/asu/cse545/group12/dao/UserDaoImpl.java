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
import org.springframework.transaction.annotation.Transactional;

import com.asu.cse545.group12.domain.User;

public class UserDaoImpl implements UserDao {

	SessionFactory sessionfactory;
	@Override
	@Transactional
	public int insertRow(User user) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession(); 
		Transaction tx = session.beginTransaction();  
		session.saveOrUpdate(user);  
		tx.commit();  
		Serializable id = session.getIdentifier(user);  
		session.close();  
		return (Integer) id; 
	}
}