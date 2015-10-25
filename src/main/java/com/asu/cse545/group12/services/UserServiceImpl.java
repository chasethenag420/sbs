package com.asu.cse545.group12.services;


import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.hashing.HashGenerator;
import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.email.EmailSenderAPI;


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
	public int insertRow(Users user){

		UserPII userpii = user.getUserpii();
		user.setUserStatus(Const.INACTIVE);
		user.setOTP("");
		user.setPassword(hashGenerator.getHashedPassword(user.getPassword()));
		java.util.Date date = Calendar.getInstance().getTime();
		user.setLastModifieddate(date);
		user.setRegistrationDate(date);
		user.setNumAttempts(new Integer(0));

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
		//create OTP and email the OTP to user
		String OTP = generateOTP();
		user.setOTP(OTP);
		sendOTPviaEmail(user);
		return userDao.insertRow(user);
	}

	@Override
	public int insertRowForEmployee(Users user){

		UserPII userpii = user.getUserpii();
		user.setUserStatus(Const.ACTIVE);
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
	public Users getUserByUserId(int userId){
		return userDao.getUserByUserId(userId);
	}
	@Override
	public int updateRow(Users user){
		return userDao.updateRow(user);
	}
	@Override
	public int deActivateUserByUserName(String userName){
		Users user = getUserByUserName(userName);
		user.setUserStatus(Const.INACTIVE);
		return updateRow(user);
	}
	@Override
	public int deActivateUserByUserId(int userId){
		Users user = userDao.getUserByUserId(userId);
		user.setUserStatus(Const.INACTIVE);
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
		if(account==null) {
			return null;
		}
		return userDao.getUserByUserId(account.getUserId());
	}

	//generate OTP
	public String generateOTP()
	{
		String uuid = UUID.randomUUID().toString();
		if(logger.isDebugEnabled()){
			logger.debug("New generated OTP: "+uuid.substring(0, 8));
		}
		return uuid.substring(0, 8);
	}


	//sent the OTP to user email
	public void sendOTPviaEmail(Users user)
	{
		String configFile = "com/asu/cse545/group12/email/mail-config.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configFile);

		// @Service("emailService") <-- same annotation you specified in crunchifyEmailAPI.java
		EmailSenderAPI emailAPI = (EmailSenderAPI) context.getBean("emailSenderService");
		String toAddr = user.getEmailId();

		// email subject
		String subject = "Successful Registration";

		// email body
		String body = "Dear "+user.getFirstName()+" "+user.getLastName()+",\n One Time Password for your account creation. \n\n OTP: "+user.getOTP()+"\n\n You need to input the OTP in the prompt for successful creation of account. \n Have a good day!";

		emailAPI.setToEmailAddress(toAddr);
		emailAPI.setBody(body);
		emailAPI.setSubject(subject);
		emailAPI.sendEmail();
		context.close();
	}

	@Override
	public int updateRowForOTP(Users user){
		String OTP = generateOTP();
		user.setOTP(OTP);
		sendOTPviaEmail(user);
		return userDao.updateRow(user);
	}

	@Override
	public int updateRowForPassword(Users user){
		user.setPassword(hashGenerator.getHashedPassword(user.getPassword()));
		return userDao.updateRow(user);
	}

	public static void main(String[] args){
		
	}

}

