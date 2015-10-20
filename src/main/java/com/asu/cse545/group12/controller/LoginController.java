package com.asu.cse545.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;


@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginForm(@RequestParam(value = "error", required = false) String error,HttpServletRequest request) {
		//logs debug message
		ModelAndView model = new ModelAndView();
		if(logger.isDebugEnabled()){
			logger.debug("Login page is requested");
		}
		if (error != null) {
			model.addObject("error", 
                           getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
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
	

	private String getErrorMessage(HttpServletRequest request, String string) {
		// TODO Auto-generated method stub
		Exception exception = 
                (Exception) request.getSession().getAttribute(string);
		
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		}else if(exception instanceof LockedException) {
			error = exception.getMessage();
		}else{
			error = "Invalid username and password!";
		}
		
		return error;
	}

	

}

