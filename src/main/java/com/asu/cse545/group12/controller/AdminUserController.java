package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.SystemAccessDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.SystemAccess;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.email.EmailSenderAPI;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class AdminUserController {
	private static final Logger logger = Logger.getLogger(AdminUserController.class);

	@Autowired
	UserService userService;

	@Autowired
	AuthorizationDao  authorizationDao;

	@Autowired
	CreateExternalUserValidator validator;
	@Autowired
	SystemAccessDao systemAccessDao;


	@RequestMapping(value = "/createEmployee", method = RequestMethod.GET)
	public ModelAndView getSignUpForm(@ModelAttribute("userpii") UserPII userpii,@Valid  @ModelAttribute("user") Users user,BindingResult result) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("create employee form");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("user", new Users());
		modelView.setViewName("createEmployee");

		return modelView;
	} 

	@RequestMapping(value = "/systemAccess", method = RequestMethod.GET)
	public ModelAndView getSystemAccess() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("System Access Log requested");
		}
		ModelAndView modelView = new ModelAndView();
		List<SystemAccess> systemAccessList= systemAccessDao.getAllSystemAccess();
		modelView.addObject("systemAccessList", systemAccessList);
		modelView.setViewName("systemAccess");

		return modelView;
	} 

	@RequestMapping( value= "createEmployeeUser", method = RequestMethod.POST)
	public ModelAndView registerEmployee(@ModelAttribute("userpii") UserPII userpii, @Valid @ModelAttribute("user") Users user, BindingResult result, Model model, HttpServletRequest request) {


		Map map = new HashMap();
		map.put("user", user);
		map.put("type", "createEmployee");
		validator.validate(map, result);

		logger.debug("******************************createEmployeeUser: ");
		if (result.hasErrors()) {
			logger.debug("******************************createEmployeeUser errored: ");
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", user);
			modelView.setViewName("createEmployee");
			modelView.addObject("message", "There are some errors in the input");
			return modelView;
		} else {		

			//create username and password for internal employee
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String username = firstName.charAt(0)+lastName;
			username = username.toLowerCase();
			int counter = 1;
			int i=0;
			//restriction on username and password is that it should be of 6 length
			if(username.length()<6)
			{
				for(; i< (6-username.length()); i++)
					username +=(i+1);
			}
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
			userService.insertRowForEmployee(user);
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", user);
			modelView.addObject("userpii", userpii);
			modelView.addObject("message", "User is created successfully and account credentials have been emailed to user");
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


	@RequestMapping(value = "/demandPIIInformation", method = RequestMethod.GET)
	public ModelAndView getCreditForm(HttpServletRequest request) {


		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("demandPIIInformation Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("form", new Form());
		modelView.setViewName("demandPIIInformation");
		return modelView;
	}

	@RequestMapping(value = "createPIIInformationRequest", method = RequestMethod.POST)
	public ModelAndView createPIIInformationRequest(@Valid @ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("creditPIIInformationRequest:");
		}

		Map<String, String> formMap = form.getMap();
		String ssn = formMap.get("ssn");
		if(logger.isDebugEnabled()){
			logger.debug("************************8 SSN"+ssn);
		}
		String username = (String) request.getSession().getAttribute("username");
		Integer SSN1;
		try
		{
			SSN1= Integer.parseInt(ssn);
		}
		catch(Exception e) {
			e.printStackTrace();
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", form);
			modelView.setViewName("demandPIIInformation");
			return modelView;
		}

		Users user = userService.getUserByUserName(username);
		if(user != null)
		{
			Authorization authorization = new Authorization();
			authorization.setAuthorizedToUserId(user.getUserId());
			authorization.setAssignedToRole(5);
			authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
			authorization.setRequestDescription(""+SSN1);
			authorization.setRequestType(Const.PII_ACCESS);
			authorization.setRequestStatus(Const.PENDING);
			authorization.setTransactionId(0);
			authorizationDao.insertRow(authorization);

			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", new Form());
			modelView.setViewName("demandPIIInformation");
			modelView.addObject("successfulMessage", "Request for accessing the PII information is sent");
			return modelView;
		}
		else
		{
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("login");
			return modelView;
		}


	}


}

