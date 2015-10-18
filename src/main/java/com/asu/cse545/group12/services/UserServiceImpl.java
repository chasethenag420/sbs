package com.asu.cse545.group12.services;


import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asu.cse545.group12.hashing.HashGenerator;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userDao;
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	private HashGenerator hashGenerator;
	
	@Override
	public int insertRow(Users user) {
		
		return userDao.insertRow(user);
	}
	@Override
	public int insertRow(Users user, UserPII userpii){

		user.setUserStatus("inactive");
		user.setPassword(hashGenerator.getHashedPassword(user.getPassword()));
		java.util.Date date = Calendar.getInstance().getTime();
		user.setLastModifieddate(date);
		user.setRegistrationDate(date);
		/* Role 
		 * 0 none
		 * 1 individual
		 * 2 merchant
		 * 3 regular
		 * 4 manager
		 * 5 admin
		 * */
		userpii.setSsn(hashGenerator.getHashedPassword(userpii.getSsn()));
		userpii.setUser(user);
		user.setUserpii(userpii);
		if(logger.isDebugEnabled()){
			logger.debug("User Data: "+user.toString());
		}
		if(logger.isDebugEnabled()){
			logger.debug("UserPII data:"+userpii.toString());
		}
		
		return userDao.insertRow(user);
	}
	
	@Override
	public int insertRowForEmployee(Users user, UserPII userpii){

		user.setUserStatus("active");
		user.setPassword(hashGenerator.getHashedPassword(user.getPassword()));
		java.util.Date date = Calendar.getInstance().getTime();
		user.setLastModifieddate(date);
		user.setRegistrationDate(date);
		/* Role 
		 * 0 none
		 * 1 individual
		 * 2 merchant
		 * 3 regular
		 * 4 manager
		 * 5 admin
		 * */
		userpii.setSsn(hashGenerator.getHashedPassword(userpii.getSsn()));
		userpii.setUser(user);
		user.setUserpii(userpii);
		if(logger.isDebugEnabled()){
			logger.debug("User Data: "+user.toString());
		}
		if(logger.isDebugEnabled()){
			logger.debug("UserPII data:"+userpii.toString());
		}
		
		return userDao.insertRow(user);
	}
	@Override
	public boolean isUserEnabled(Users user){
		if("active".equalsIgnoreCase(user.getUserStatus())){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public Users getUserByUserName(String userName){
		return userDao.getUserByUserName(userName);
	}
	@Override
	public int updateRow(Users user){
		return userDao.updateRow(user);
	}
	@Override
	public int deActivateUserByUserName(String userName){
		Users user = getUserByUserName(userName);
		user.setUserStatus("inactive");
		return updateRow(user);
	}
	@Override
	public int deActivateUserByUserId(int userId){
		Users user = userDao.getUserByUserId(userId);
		user.setUserStatus("inactive");
		return updateRow(user);
	}
	@Override
	public List<Users> getUsersByFirstName(String firstName){
		return userDao.getUsersByFirstName(firstName);
	}
	@Override
	public List<Users> getUsersByLastName(String lastName){
		return userDao.getUsersByLastName(lastName);
	}
	@Override
	public List<Users> getUsersByPhoneNumber(String phoneNumber){
		return userDao.getUsersByPhoneNumber(phoneNumber);
	}
	@Override
	public List<Users> getUsersByEmailId(String emailId){
		return userDao.getUsersByEmailId(emailId);
	}
	@Override
	public Users getUsersByAccountNumber(int accountNumber){
		Account account=accountDao.getAccountByAccountNumber(accountNumber);
		return userDao.getUserByUserId(account.getUserId());
	}
}

