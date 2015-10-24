package com.asu.cse545.group12.authentication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.asu.cse545.group12.dao.UserloginDetailsDao;

@Component("authenticationProvider")
public class LimitLoginAuthentication extends DaoAuthenticationProvider{
	@Autowired
	UserloginDetailsDao userloginDetailsDao;
	

	public Authentication authenticate(Authentication authentication) 
          throws AuthenticationException {

	  try {
		Authentication auth = super.authenticate(authentication);

		//if reach here, means login success, else an exception will be thrown
		//reset the user_attempts
		userloginDetailsDao.resetFailedAttempts(authentication.getName());
			
		return auth;
			
	  }catch (BadCredentialsException e) {	
			
		//invalid login, update to user_attempts
		  userloginDetailsDao.updateFailedAttempts(authentication.getName());
		throw e;
			
	  } catch (LockedException e){
			
		//this user is locked!
		String error = "";
		int userAttempts = 
				userloginDetailsDao.getUserAttempts(authentication.getName());
		
            if(userAttempts==3)
            {
			error = "User account is locked! <br /><br />Username : " 
                         + authentication.getName() + "<br />Last Attempts : " + userAttempts;
		}else{
			error = e.getMessage();
		}
			
	  throw new LockedException(error);
	}
	
}

}

