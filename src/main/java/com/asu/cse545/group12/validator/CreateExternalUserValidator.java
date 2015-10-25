package com.asu.cse545.group12.validator;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.UserService;

import org.springframework.validation.ValidationUtils;


public class CreateExternalUserValidator implements Validator{

	private static final Logger logger = Logger.getLogger(CreateExternalUserValidator.class);
	private HttpServletRequest request;

	@Autowired
	UserService userService;


	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public boolean supports(Class aClass) {
		//just validate the Login instances
		return Users.class.isAssignableFrom(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Map map = (HashMap) obj;
		Users user = (Users) map.get("user");
		UserPII userpii = (UserPII) map.get("userpii");
		String type = (String) map.get("type");

		if("signup".equalsIgnoreCase(type))
		{
			//do validation here for username
			try
			{
				String username = user.getUserName();
				if("".equals(username))
				{
					errors.rejectValue("user.userName", "not-string", "Enter Username");
				}
				else
				{
					if(!username.matches("[A-Za-z0-9]"))
					{
						errors.rejectValue("user.userName", "not-string", "Enter only alphanumeric Username ");
					}
					else
					{
						if(!username.matches("{6,15}"))
						{
							errors.rejectValue("user.userName", "not-string", "Length of Username must be between 6 and 15");
						}
						else
						{
							//we need to check the username is available or not
							Users existingUser = userService.getUserByUserName(username);
							if(existingUser != null)
							{
								errors.rejectValue("user.userName", "not-string", "This Username is not available");
							}
						}
					}
				}
			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
				}
				errors.rejectValue("user.userName", "not-string", "Enter valid Username");
			}

			//do validation here for Password

			try
			{
				String password = user.getPassword();
				if("".equals(password))
				{
					errors.rejectValue("user.password", "not-string", "Enter Password");
				}
				else
				{
					if(!password.matches("[A-Za-z0-9]"))
					{
						errors.rejectValue("user.password", "not-string", "Enter only alphanumeric Password ");
					}
					else
					{
						if(!password.matches("{6,15}"))
						{
							errors.rejectValue("user.password", "not-string", "Length of Password must be between 6 and 15");
						}
					}
				}
			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
				}
				errors.rejectValue("user.password", "not-string", "Enter valid Password");
			}
		}

		//validate first name
		try
		{
			String firstName = user.getFirstName();
			if("".equals(firstName))
			{
				errors.rejectValue("user.firstName", "not-string", "Enter First Name");
			}
			else
			{
				if(!firstName.matches("[A-Za-z]"))
				{
					errors.rejectValue("user.firstName", "not-string", "Enter only alphabetic First Name");
				}
				else
				{
					if(!firstName.matches("{1,15}"))
					{
						errors.rejectValue("user.firstName", "not-string", "Length of First Name must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("user.firstName", "not-string", "Enter valid First Name");
		}

		//validate middle name
		try
		{
			String middleName = user.getMiddleName();

			if(!middleName.matches("[A-Za-z]"))
			{
				errors.rejectValue("user.middleName", "not-string", "Enter only alphabetic Middle Name");
			}
			else
			{
				if(!middleName.matches("{1,15}"))
				{
					errors.rejectValue("user.middleName", "not-string", "Length of Middle Name must be between 1 and 15");
				}
			}

		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("user.middleName", "not-string", "Enter valid Middle Name");
		}

		//validate last name
		try
		{
			String lastName = user.getLastName();
			if("".equals(lastName))
			{
				errors.rejectValue("user.lastName", "not-string", "Enter Last Name");
			}
			else
			{
				if(!lastName.matches("[A-Za-z ]"))
				{
					errors.rejectValue("user.lastName", "not-string", "Enter only alphabetic Last Name");
				}
				else
				{
					if(!lastName.matches("{1,15}"))
					{
						errors.rejectValue("user.lastName", "not-string", "Length of Last Name must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("user.lastName", "not-string", "Enter valid Last Name");
		}

		//validate gender
		try
		{
			String gender = user.getGender();
			if("".equals(gender))
			{
				errors.rejectValue("user.gender", "not-string", "Select Gender");
			}
			else
			{
				if(!("male".equalsIgnoreCase(gender) || "female".equalsIgnoreCase(gender)))
				{
					errors.rejectValue("user.gender", "not-string", "Select valid Gender");
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("user.gender", "not-string", "Select valid Gender");
		}

		//validate SSN
		try
		{
			String ssn = userpii.getSsn();
			if("".equals(ssn))
			{
				errors.rejectValue("userpii.ssn", "not-string", "Enter SSN");
			}
			else
			{
				if(!ssn.matches("[0-9]"))
				{
					errors.rejectValue("userpii.ssn", "not-string", "Enter only numbers");
				}
				else
				{
					if(ssn.length() != 9)
					{
						errors.rejectValue("userpii.ssn", "not-number", "Length of SSN must be 9");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("userpii.ssn", "not-string", "Enter valid SSN");
		}

		//validate Date of Birth
		try
		{
			SimpleDateFormat  format = new SimpleDateFormat("MM/dd/yyyy");
			Date dateOfBirth = format.parse(userpii.getDateOfBirth().toString());
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("userpii.DateOfBirth", "not-string", "Enter valid Date of Birth (MM/dd/yyyy)");
		}

		//validate address
		try
		{
			String address = user.getAddress();
			if("".equals(address))
			{
				errors.rejectValue("user.address", "not-string", "Enter Address");
			}
			else
			{
				if(!address.matches("[a-zA-Z0-9# ]"))
				{
					errors.rejectValue("user.address", "not-string", "Enter valid Address([a-zA-Z0-9# ])");
				}
				else
				{
					if(!address.matches("{5,50}"))
					{
						errors.rejectValue("user.address", "not-string", "Length of Address must be between 5 and 50");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("user.address", "not-string", "Enter valid Address");
		}

	}   
}

