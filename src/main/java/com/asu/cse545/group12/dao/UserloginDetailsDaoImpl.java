package com.asu.cse545.group12.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.domain.Users;

public class UserloginDetailsDaoImpl implements UserloginDetailsDao{
	
	@Autowired
	SessionFactory sessionfactory;
	private static final int max_attempts=3;

	@Override
	public void updateFailedAttempts(String username) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Criteria criteria = session.createCriteria(Users.class)
                .add(Restrictions.eq("userName", username));
		 Users user = (Users) criteria.uniqueResult();
		 int numberattempts = user.getNumAttempts();
		 if(numberattempts <max_attempts)
		 {
		 user.setNumAttempts(numberattempts+1);;
		 session.update(user);
		 Transaction tx = session.beginTransaction();
		 tx.commit();
		 }
		 else
		 {
			 user.setUserStatus("inactive");
			 session.update(user);
			 Transaction tx = session.beginTransaction();
			 tx.commit();
		 }
		 
		session.close();
	}

	@Override
	public void resetFailedAttempts(String username) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		 Criteria criteria = session.createCriteria(Users.class)
               .add(Restrictions.eq("userName", username));
		 Users user = (Users) criteria.uniqueResult();
		 user.setNumAttempts(0);
		 session.update(user);
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		
	}

	@Override
	public int getUserAttempts(String username) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Session session = sessionfactory.openSession();
				 Criteria criteria = session.createCriteria(Users.class)
			                .add(Restrictions.eq("userName", username));
					 Users user = (Users) criteria.uniqueResult();
					 int userAttempts = user.getNumAttempts();
				return userAttempts;
	
	}

}
