package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

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
		modelView.addObject("userPII", new UserPII());
		modelView.setViewName("signup");
		
		return modelView;
	}
}




/*package com.asu.cse545.group12.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class LoginController {
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }

	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");

	  return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("403");
	  return model;

	}
}
*/