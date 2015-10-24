package com.asu.cse545.group12.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.domain.Authorization;
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

	@Override
	public int getRoleid(String roledesc) {
		// TODO Auto-generated method stub
		List<Role> role;
		Session session = sessionfactory.getCurrentSession();
		Criteria cr = session.createCriteria(Role.class);
		cr.add(Restrictions.eq("roleDescription", roledesc));
		role = cr.list();
		return role.get(0).getRoleId();
	}

}
