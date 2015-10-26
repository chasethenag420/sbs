package com.asu.cse545.group12.validator;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.UserService;

import org.springframework.validation.ValidationUtils;


public class CreateExternalUserValidator implements Validator{

	private static final Logger logger = Logger.getLogger(CreateExternalUserValidator.class);


	@Autowired
	UserService userService;



	public boolean supports(Class aClass) {
		//just validate the Users instances
		return Form.class.isAssignableFrom(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Map map = (HashMap) obj;
		Users user = (Users) map.get("user");
		UserPII userpii = user.getUserpii();
		String type = (String) map.get("type");


		if("signup".equalsIgnoreCase(type))
		{
			//do validation here for username
			try
			{
				String username = user.getUserName();
				if("".equals(username))
				{
					if (logger.isDebugEnabled()) {
						logger.debug("****************Error : Exception username" );
					}
					errors.rejectValue("userName", "not-string", "Enter Username");
				}
				else
				{
					if(!username.matches("[A-Za-z0-9]+"))
					{
						if (logger.isDebugEnabled()) {
							logger.debug("****************Error : Exception username" );
						}
						errors.rejectValue("userName", "not-string", "Enter only alphanumeric Username ");

					}
					else
					{
						if(!(username.length()>5 && username.length()<16))
						{
							if (logger.isDebugEnabled()) {
								logger.debug("****************Error : Exception username" );
							}
							errors.rejectValue("userName", "not-string", "Length of Username must be between 6 and 15 including");
						}
						else
						{
							//we need to check the username is available or not
							if (logger.isDebugEnabled()) {
								logger.debug("****************Error : userService" );
							}
							Users existingUser = userService.getUserByUserName(username);
							if (logger.isDebugEnabled()) {
								logger.debug("****************Error : Exception username" );
							}
							if(existingUser != null)
							{
								if (logger.isDebugEnabled()) {
									logger.debug("****************Error : Exception username" );
								}
								errors.rejectValue("userName", "not-string", "This Username is not available");
							}
						}
					}
				}
			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					e.printStackTrace();
				}
				errors.rejectValue("userName", "not-string", "Enter valid Username");
			}

			//do validation here for Password

			try
			{
				String password = user.getPassword();
				if("".equals(password))
				{
					errors.rejectValue("password", "not-string", "Enter Password");
				}
				else
				{
					if(!password.matches("[A-Za-z0-9]+"))
					{
						errors.rejectValue("password", "not-string", "Enter only alphanumeric Password ");
					}
					else
					{
						if(!(password.length()>5 && password.length()<16))
						{
							errors.rejectValue("password", "not-string", "Length of Password must be between 6 and 15");
						}
					}
				}
			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					e.printStackTrace();
				}
				errors.rejectValue("password", "not-string", "Enter valid Password");
			}
		}

		//validate first name
		try
		{
			String firstName = user.getFirstName();
			if("".equals(firstName))
			{
				errors.rejectValue("firstName", "not-string", "Enter First Name");
			}
			else
			{
				if(!firstName.matches("[A-Za-z ]+"))
				{
					errors.rejectValue("firstName", "not-string", "Enter only alphabetic First Name");
				}
				else
				{
					if(!(firstName.length()>0 && firstName.length()<16))
					{
						errors.rejectValue("firstName", "not-string", "Length of First Name must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("firstName", "not-string", "Enter valid First Name");
		}

		//validate middle name
		try
		{
			String middleName = user.getMiddleName();

			if(!middleName.matches("[A-Za-z ]+"))
			{
				errors.rejectValue("middleName", "not-string", "Enter only alphabetic Middle Name");
			}
			else
			{
				if(!(middleName.length()>=0 && middleName.length()<16))
				{
					errors.rejectValue("middleName", "not-string", "Length of Middle Name must be between 0 and 15");
				}
			}

		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("middleName", "not-string", "Enter valid Middle Name");
		}

		//validate last name
		try
		{
			String lastName = user.getLastName();
			if("".equals(lastName))
			{
				errors.rejectValue("lastName", "not-string", "Enter Last Name");
			}
			else
			{
				if(!lastName.matches("[A-Za-z ]+"))
				{
					errors.rejectValue("lastName", "not-string", "Enter only alphabetic Last Name");
				}
				else
				{
					if(!(lastName.length()>0 && lastName.length()<16))
					{
						errors.rejectValue("lastName", "not-string", "Length of Last Name must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("lastName", "not-string", "Enter valid Last Name");
		}

		//validate gender
		try
		{
			String gender = user.getGender();
			if("".equals(gender))
			{
				errors.rejectValue("gender", "not-string", "Select Gender");
			}
			else
			{
				if(!("male".equalsIgnoreCase(gender) || "female".equalsIgnoreCase(gender)))
				{
					errors.rejectValue("gender", "not-string", "Select valid Gender");
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("gender", "not-string", "Select valid Gender");
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
				if(!ssn.matches("[0-9]+"))
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
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up: UserPII : "+userpii );
			}

			if(userpii.getDateOfBirth() == null)
			{
				errors.rejectValue("userpii.DateOfBirth", "not-string", "Enter valid Date of Birth (MM/dd/yyyy)");
			}

		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("userpii.DateOfBirth", "not-string", "Enter valid Date of Birth (MM/dd/yyyy)");
		}

		//validate address
		try
		{
			String address = user.getAddress();
			if("".equals(address))
			{
				errors.rejectValue("address", "not-string", "Enter Address");
			}
			else
			{
				if(!address.matches("[a-zA-Z0-9# ]+"))
				{
					errors.rejectValue("address", "not-string", "Enter valid Address([a-zA-Z0-9# ])");
				}
				else
				{
					if(!(address.length()>4 && address.length()<51))
					{
						errors.rejectValue("address", "not-string", "Length of Address must be between 5 and 50");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("address", "not-string", "Enter valid Address");
		}

		//validate city
		try
		{
			String city = user.getCity();
			if("".equals(city))
			{
				errors.rejectValue("city", "not-string", "Enter City");
			}
			else
			{
				if(!city.matches("[a-zA-Z ]+"))
				{
					errors.rejectValue("city", "not-string", "Enter valid City([a-zA-Z ])");
				}
				else
				{
					if(!(city.length()>0 && city.length()<16))
					{
						errors.rejectValue("city", "not-string", "Length of City must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("city", "not-string", "Enter valid City");
		}

		//validate state
		try
		{
			String state = user.getState();
			if("".equals(state))
			{
				errors.rejectValue("state", "not-string", "Enter State");
			}
			else
			{
				if(!state.matches("[a-zA-Z ]+"))
				{
					errors.rejectValue("state", "not-string", "Enter valid State([a-zA-Z ])");
				}
				else
				{
					if(!(state.length()>0 && state.length()<16))
					{
						errors.rejectValue("state", "not-string", "Length of State must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("state", "not-string", "Enter valid State");
		}

		//validate country
		try
		{
			String country = user.getCountry();
			if("".equals(country))
			{
				errors.rejectValue("country", "not-string", "Enter country");
			}
			else
			{
				if(!country.matches("[a-zA-Z ]+"))
				{
					errors.rejectValue("country", "not-string", "Enter valid country([a-zA-Z ])");
				}
				else
				{
					if(!(country.length()>0 && country.length()<16))
					{
						errors.rejectValue("country", "not-string", "Length of country must be between 1 and 15");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			errors.rejectValue("country", "not-string", "Enter valid country");
		}

		//validate ZIP
		try
		{
			String zip = user.getZipcode();
			if("".equals(zip))
			{
				errors.rejectValue("zipcode", "not-string", "Enter Zipcode");
			}
			else
			{
				if(!zip.matches("[0-9]+"))
				{
					errors.rejectValue("zipcode", "not-string", "Enter numbers only");
				}
				else
				{
					if(zip.length()!=5)
					{
						errors.rejectValue("zipcode", "not-string", "Length of Zipcode must be 5");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();

			}
			errors.rejectValue("zipcode", "not-string", "Enter valid Zipcode");
		}

		//validate Role
		try
		{
			String role = user.getRoleId()+"";
			if("".equals(role))
			{
				errors.rejectValue("roleId", "not-string", "Select Role");
			}
			else
			{
				if("signup".equalsIgnoreCase(type))
				{
					if(!role.matches("[1-2]"))
					{
						errors.rejectValue("roleId", "not-string", "Select valid Role");
					}

					else
					{
						if(role.length()!=1)
						{
							errors.rejectValue("roleId", "not-string", "Select valid Role");
						}
					}
				}
				else
				{
					if(!role.matches("[3-5]"))
					{
						errors.rejectValue("roleId", "not-string", "Select valid Role");
					}

					else
					{
						if(role.length()!=1)
						{
							errors.rejectValue("roleId", "not-string", "Select valid Role");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();

			}
			errors.rejectValue("roleId", "not-string", "Select valid Role");
		}

		//validate phone number
		try
		{
			String phonenumber = user.getPhoneNumber();
			if("".equals(phonenumber))
			{
				errors.rejectValue("phoneNumber", "not-string", "Enter Phone Number");
			}
			else
			{
				if(!phonenumber.matches("[1-9][0-9]*"))
				{
					errors.rejectValue("phoneNumber", "not-string", "Enter valid Phone Number");
				}
				else
				{
					if(phonenumber.length()!=10)
					{
						errors.rejectValue("phoneNumber", "not-string", "Length of Phone Number must be 10");
					}
				}
			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				e.printStackTrace();

			}
			errors.rejectValue("phoneNumber", "not-string", "Enter valid Phone Number");
		}

		//validate email
		try
		{
			String email = user.getEmailId();
			if("".equals(email))
			{
				errors.rejectValue("emailId", "not-string", "Enter Email ID");
			}
			else
			{
				Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
				Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
				if(!matcher.find())
				{
					errors.rejectValue("emailId", "not-string", "Enter valid Email ID");
				}

			}
		}
		catch(Exception e)
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Sign up validation : Exception\n+"+e.getStackTrace() );
			}
			errors.rejectValue("emailId", "not-string", "Enter valid Email ID");
		}
	}   
}

