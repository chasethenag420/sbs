package com.asu.cse545.group12.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.asu.cse545.group12.dao.UserPiiDao;

import com.asu.cse545.group12.domain.UserPII;


@Service("userPiiServiceImpl")
public class UserPiiServiceImpl implements UserPiiService{
	@Autowired
	UserPiiDao userPiiDao;

	@Override
	public int insertRow(UserPII userpii) {
		// TODO Auto-generated method stub
		userPiiDao.insertRow(userpii);
		return userpii.getUserId();
	}

	
	
	

}
