package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.dao.SecurityQuestionsDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.SecurityQuestions;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.email.EmailSenderAPI;
import com.asu.cse545.group12.hashing.HashGenerator;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.TransactionInputValidator;

@Controller
public class ForgetPasswordController {
	private static final Logger logger = Logger.getLogger(ForgetPasswordController.class);


	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SecurityQuestionsDao securityQuestionsDao;

	@Autowired
	private HashGenerator hashGenerator;
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public ModelAndView getCreditForm(HttpServletRequest request) {

		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("ForgetPassword Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("form", new Form());

		modelView.setViewName("forgetPassword");
		return modelView;
	}

	@RequestMapping(value = "setForgetPassword", method = RequestMethod.POST)
	public ModelAndView setForgetPassword(@Valid @ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("Credit Amount:");
		}
		//TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		//transactionInputValidator.validate(form, result);

		Map<String, String> formMap = form.getMap();
		String username = formMap.get("username");
		String email = formMap.get("email");
		if(username == null || username.equals("") || email == null || email.equals("") || !username.matches("[a-zA-Z0-9]+"))
		{
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("form", new Form());
				modelView.addObject("errorMessage", "Username and/or email is not in correct format");

				modelView.setViewName("forgetPassword");
				return modelView;
		}
			
			else
			{
				Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
				Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
				if(!matcher.find())
				{
					ModelAndView modelView = new ModelAndView();
					modelView.addObject("form", new Form());
					modelView.addObject("errorMessage", "Username and/or email is not in correct format");

					modelView.setViewName("forgetPassword");
					return modelView;
				}
			}
			
		

		Users user = userDao.getUserByUserName(username);
		if(user == null)
		{
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("form", new Form());
				modelView.addObject("errorMessage", "User does not exist");

				modelView.setViewName("forgetPassword");
				return modelView;
		}
		
		SecurityQuestions securityQuestions = securityQuestionsDao.getSecurityQuestionsByUserId(user.getUserId());
		if(securityQuestions == null)
		{
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("errorMessage", "User does not set the security questions. So, password cannot reset. \n Contact bank official.");
			modelView.addObject("form", new Form());

			modelView.setViewName("forgetPassword");
			return modelView;
		}
		ModelAndView modelView = new ModelAndView();
        Form newForm = new Form();
        newForm.getMap().put("question1", securityQuestions.getQuestion1());
        newForm.getMap().put("question2", securityQuestions.getQuestion2());
        newForm.getMap().put("question3", securityQuestions.getQuestion3());
        newForm.getMap().put("username", username);
        newForm.getMap().put("email", email);
		modelView.addObject("form", newForm);

		modelView.setViewName("securityQuestions");
		return modelView;


	}
	
	@RequestMapping(value = "validateSecurityQuestions", method = RequestMethod.POST)
	public ModelAndView validateSecurityQuestions(@Valid @ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("validateSecurityQuestions");
		}
		//TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		//transactionInputValidator.validate(form, result);

		Map<String, String> formMap = form.getMap();
		String question1 = formMap.get("question1");
		String answer1 = formMap.get("answer1");
		String question2 = formMap.get("question2");
		String answer2 = formMap.get("answer2");
		String question3 = formMap.get("question3");
		String answer3 = formMap.get("answer3");
		String email = formMap.get("email");
		String username = formMap.get("username");
		//username = null; //testing
		Users user = userDao.getUserByUserName(username);
		if(user == null)
		{
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", new Form());
			modelView.addObject("errorMessage", "User does not exit. Enter valid username and email.");

			modelView.setViewName("forgetPassword");
			return modelView;
		}
		
		SecurityQuestions securityQuestions = securityQuestionsDao.getSecurityQuestionsByUserId(user.getUserId());
		if(securityQuestions == null)
		{
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", new Form());
			modelView.addObject("errorMessage", "No Security questions found for the user.");

			modelView.setViewName("forgetPassword");
			return modelView;
		}

		if(question1.equals(securityQuestions.getQuestion1()) && question2.equals(securityQuestions.getQuestion2())  && question3.equals(securityQuestions.getQuestion3()) && answer1.equals(securityQuestions.getAnswer1()) && answer2.equals(securityQuestions.getAnswer2()) && answer3.equals(securityQuestions.getAnswer3()))
		{
			if(email.equals(user.getEmailId()))
			{
				String password = generateOTP();
				user.setPassword(password);
				userService.updateRowForPassword(user);
				sendOTPviaEmail(user, password);
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("successfulMessage", "A new password is generated and is sent to your registered email.");
				modelView.setViewName("successful");
				return modelView;
			}
			else
				return new ModelAndView("login");
			
		}
		else
		{
			
			
			ModelAndView modelView = new ModelAndView();
	        Form newForm = new Form();
	        newForm.getMap().put("question1", securityQuestions.getQuestion1());
	        newForm.getMap().put("question2", securityQuestions.getQuestion2());
	        newForm.getMap().put("question3", securityQuestions.getQuestion3());
	        newForm.getMap().put("username", username);
	        newForm.getMap().put("email", email);
			modelView.addObject("form", newForm);
			modelView.addObject("errorMessage", "Security questions  or answers do not match with stored data.");
			modelView.setViewName("securityQuestions");
			return modelView;
		}
		


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
		public void sendOTPviaEmail(Users user, String password)
		{
			String configFile = "com/asu/cse545/group12/email/mail-config.xml";
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configFile);
	 
			// @Service("emailService") <-- same annotation you specified in crunchifyEmailAPI.java
			EmailSenderAPI emailAPI = (EmailSenderAPI) context.getBean("emailSenderService");
			String toAddr = user.getEmailId();
	 
			// email subject
			String subject = "Password Change";
	 
			// email body
			String body = "Dear "+user.getFirstName()+" "+user.getLastName()+",\n New password for your account is generated. \n\n Password: "+password+"\n\n Kindly, change the password after you login. \n Have a good day!";
			
			emailAPI.setToEmailAddress(toAddr);
			emailAPI.setBody(body);
			emailAPI.setSubject(subject);
			emailAPI.sendEmail();
		}
		
		
		@RequestMapping(value = "changeUserPassword", method = RequestMethod.POST)
		public ModelAndView changePassword(HttpServletRequest request) {

			//logs debug message
			if(logger.isDebugEnabled()){
				logger.debug("changePassword Screen is executed!");
			}
			ModelAndView modelView = new ModelAndView();

			modelView.addObject("form", new Form());

			modelView.setViewName("changePassword");
			return modelView;
		}

		@RequestMapping(value = "setNewPassword", method = RequestMethod.POST)
		public ModelAndView setNewPassword(@Valid @ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {
			if(logger.isDebugEnabled()){
				logger.debug("Setting new password:");
			}
			//TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
			//transactionInputValidator.validate(form, result);

			ModelAndView modelView = new ModelAndView();
			
			Map<String, String> formMap = form.getMap();
			//String currentPassword = formMap.get("currentPassword");
			String newPassword = formMap.get("newPassword");
			String confirmPassword = formMap.get("confirmPassword");
			
			
			
			
			String username = (String) request.getSession(false).getAttribute("username");
			Users user = userDao.getUserByUserName(username);
			if(logger.isDebugEnabled()){
				logger.debug("*************************User password:"+user.getPassword());
				//logger.debug("*************************current password:"+hashGenerator.getHashedPassword(currentPassword));
			}
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        //String hashedPassword = passwordEncoder.encode(currentPassword);
	        
			if(user != null /*&& passwordEncoder.matches(hashedPassword, user.getPassword()) && !(currentPassword.equals(newPassword))*/)
			{
				
				if(newPassword.equals(confirmPassword))
				{
					user.setPassword(newPassword);
					userService.updateRowForPassword(user);
					modelView.addObject("successfulMessage", "A new password is set successfully.");
					modelView.setViewName("successful");
					return modelView;
				}
				else
				{
					modelView.addObject("form", new Form());
					modelView.addObject("errorMessage", "New password does not match.");
					modelView.setViewName("changePassword");
					return modelView;
				}
			}
			else
			{
				modelView.addObject("form", new Form());
				if(user == null)
					modelView.addObject("errorMessage", "User does not exist.");
				/*else if(! passwordEncoder.matches(hashedPassword, user.getPassword()))
					modelView.addObject("errorMessage", "Current password is wrong.");*/
				else
					modelView.addObject("errorMessage", "Current password and new password must be different.");
				modelView.setViewName("changePassword");
				return modelView;
			}

		}
}
