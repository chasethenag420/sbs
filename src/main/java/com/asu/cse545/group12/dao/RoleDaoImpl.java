package com.asu.cse545.group12.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.domain.Role;

public class RoleDaoImpl implements RoleDao{
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public Role getRowById(int RoleId) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Role role = session.load(Role.class, RoleId);
		return role;
	}

}
