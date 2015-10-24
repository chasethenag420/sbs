package com.asu.cse545.group12.dao;

import java.io.Serializable;






import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asu.cse545.group12.domain.UserPII;



@Component("userPiiDaoImpl")
public class UserPiiDaoImpl implements UserPiiDao{
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public int insertRow(UserPII userpii) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(userpii);
		tx.commit();
		Serializable userId = session.getIdentifier(userpii);
		session.close();
		return (Integer) userId;
	}

	@Override
	public UserPII getRowById(int userId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		UserPII userpii = session.load(UserPII.class, userId);
		return userpii;
	}

	@Override
	public int updateRow(UserPII userpii) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(userpii);
		tx.commit();
		Serializable userId = session.getIdentifier(userpii);
		session.close();
		return (Integer) userId;
		
	}
	

}
