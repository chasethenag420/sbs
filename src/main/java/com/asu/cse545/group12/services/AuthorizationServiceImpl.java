package com.asu.cse545.group12.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Users;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	
	@Autowired
	AuthorizationDao authorizationDao;
	 
	
	
	@Override
	public int approve(Authorization authorization) {
		// TODO Auto-generated method stub
		return authorizationDao.approve(authorization);
	}

	@Override
	public List<Authorization> getNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int reject(Authorization authorization) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int forward(Authorization authorization) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int signupInsertRow(Users user) {
		Authorization authorizationRequest = new Authorization();
		authorizationRequest.setAuthorizedByUserId(user.getUserId());
		authorizationRequest.setRequestStatus("pending");
		authorizationRequest.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
		authorizationRequest.setRequestDescription("approval for account creation");
		authorizationRequest.setRequestType("signup request");
		authorizationDao.insertRow(authorizationRequest);
		
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
