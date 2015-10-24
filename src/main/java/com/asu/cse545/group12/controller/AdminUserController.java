package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.email.EmailSenderAPI;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class AdminUserController {
	private static final Logger logger = Logger.getLogger(AdminUserController.class);
	
	@Autowired
	UserService userService;
	
	
	
	@RequestMapping(value = "/createEmployee", method = RequestMethod.GET)
	public ModelAndView getSignUpForm(@ModelAttribute("userpii") UserPII userpii,@Valid  @ModelAttribute("user") Users user,BindingResult result) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("create employee form");
		}
		ModelAndView modelView = new ModelAndView();
		logger.debug("userpii "+userpii.toString());
		logger.debug("user "+user.toString());
		modelView.addObject("user", new Users());
		modelView.addObject("userpii", new UserPII());
		modelView.setViewName("createEmployee");

		return modelView;
	} 
	
	@RequestMapping( value= "createEmployeeUser", method = RequestMethod.POST)
	public ModelAndView registerEmployee(@ModelAttribute("userpii") UserPII userpii, @Valid @ModelAttribute("user") Users user, BindingResult result, Model model, HttpServletRequest request) {

		CreateExternalUserValidator validator = new CreateExternalUserValidator();
		//validator.validate(user, result);
		logger.debug("******************************createEmployeeUser: ");
		if (result.hasErrors()) {
			logger.debug("******************************createEmployeeUser errored: ");
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", user);
			modelView.addObject("userpii", userpii);
			modelView.setViewName("admin");
			modelView.addObject("message", result.toString());
			return modelView;
		} else {		

			//create username and password for internal employee
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String username = firstName.charAt(0)+lastName;
			username = username.toLowerCase();
			int counter = 1;
			while(true)
			{
				Users existingUser = userService.getUserByUserName(username);
				logger.debug("******************************Password: "+user.getPassword());
				if(existingUser == null)
				{
					//set same username to username and password
					user.setUserName(username);
					user.setPassword(username);
					userpii.setUser(user);
					if(user.getUserpii() != null)
						user.getUserpii().setUser(user);
					if(logger.isDebugEnabled()){
						logger.debug("********************************Username: "+user.getUserName());
						logger.debug("******************************Password: "+user.getPassword());
					}
					sendEmailToEmployee(user);
					break;
				}
				else
				{
					username = username+counter;
					counter++;
				}
			}
			userService.insertRowForEmployee(user,userpii);
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", user);
			modelView.addObject("userpii", userpii);
			modelView.setViewName("admin");
			return modelView;
		}
	}
	
	public void sendEmailToEmployee(Users user)
	{
				// Spring Bean file you specified in /src/main/resources folder
				String configFile = "com/asu/cse545/group12/email/mail-config.xml";
				ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configFile);
		 
				// @Service("emailService") <-- same annotation you specified in crunchifyEmailAPI.java
				EmailSenderAPI emailAPI = (EmailSenderAPI) context.getBean("emailSenderService");
				String toAddr = user.getEmailId();
		 
				// email subject
				String subject = "Successful Registration";
		 
				// email body
				String body = "Dear "+user.getFirstName()+" "+user.getLastName()+",\n You are online employee profile is created. Use following credentials to login:\n Username: "+user.getUserName()+"\n Password: "+user.getPassword()+"\n Have a good day!";
				
				emailAPI.setToEmailAddress(toAddr);
				emailAPI.setBody(body);
				emailAPI.setSubject(subject);
				emailAPI.sendEmail();
				context.close();
	}
	

}

