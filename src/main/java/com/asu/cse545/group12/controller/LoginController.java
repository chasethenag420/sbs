package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;


@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Login page is requested");
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getSignUpForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Sign up form requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("user", new Users());
		modelView.addObject("userpii", new UserPII());
		modelView.setViewName("signup");

		return modelView;
	} 
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("User logout");
		}
		

		return "logout";
	} 

}

