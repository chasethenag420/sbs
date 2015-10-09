package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;	

	@Autowired
	private PasswordEncoder passwordEncoder;


	@RequestMapping( value= "signUpExternalUser")
	public String registerUser(@ModelAttribute("userpii") UserPII userpii, @Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {

		CreateExternalUserValidator validator = new CreateExternalUserValidator();
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		} else {

			user.setUserStatus("inactive");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			java.util.Date date = Calendar.getInstance().getTime();
			user.setLastModifieddate(date);
			user.setRegistrationDate(date);
			// This need to be changed**********************************************8888
			/* Role 
			 * 5 individual
			 * 4 merchant
			 * 3 regular
			 * 2 manager
			 * 1 admin
			 * 0 none
			 * 
			 * */
			user.setRoleId(5);
			userpii.setDateOfBirth(date);
			userpii.setUser(user);
			user.setUserpii(userpii);

			if(logger.isDebugEnabled()){
				logger.debug("User Data: "+user.toString());
			}
			if(logger.isDebugEnabled()){
				logger.debug("UserPII data:"+userpii.toString());
			}

			userService.insertRow(user);
			return "successfulSignUp";
		}
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("mm/dd/yyyy"), true, 10));   
	}
	

}

