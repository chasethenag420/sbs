package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;




import java.util.Date;
import java.util.HashMap;
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

import org.springframework.stereotype.Service;

import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class SignUpController {
	private static final Logger logger = Logger.getLogger(SignUpController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	AuthorizationService  authorizationService;
	
	@Autowired
	CreateExternalUserValidator validator;

	@RequestMapping( value= "/signUp",method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("userpii") UserPII userpii, @Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {

		
		Map map = new HashMap();
		map.put("user", user);
		map.put("userpii", userpii);
		map.put("type", "signup");
		validator.validate(map, result);
		
		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", user);
			Form form = new Form();
			form.getMap().put("confirmPassword", new String(""));
			modelView.addObject("form", form);
			modelView.addObject("userpii", userpii);
			modelView.setViewName("signup");
			return modelView;
		} else {		

			userService.insertRow(user,userpii);
			ModelAndView modelView = new ModelAndView();
			Form form = new Form();
			form.getMap().put("email", new String(user.getEmailId()));
			form.getMap().put("OTP", new String(""));
			form.getMap().put("username", new String(user.getUserName()));
			
			modelView.addObject("form", form);
			modelView.setViewName("signUpOTP");
			//authorizationService.signupInsertRow(user);
			//return new ModelAndView("successfulSignUp");
			return modelView;
		}
	}
	
	
	@RequestMapping(value = "/enterSignupOTP",method = RequestMethod.POST)
	public ModelAndView enterSignUpOTP(@ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {
		
		Map<String, String> formMap = form.getMap();
		String OTP  = formMap.get("OTP");
		String username = formMap.get("username");
		Users user = userService.getUserByUserName(username);
		//System.out.println("***************************************************************** OTP: "+OTP+" username:"+username+ "opt from user: "+user.getOTP());
		if(logger.isDebugEnabled()){
			logger.debug("**********************OTP:"+OTP);
			logger.debug("**********************Usename:"+username);
			logger.debug("**********************OTP from user: "+user.getOTP());
		}
		if(OTP.equals(user.getOTP()))
		{
			authorizationService.signupInsertRow(user);
			return new ModelAndView("successfulSignUp");
		}
		else
		{
			ModelAndView modelView = new ModelAndView();
			form.getMap().put("OTP", new String(""));
			modelView.addObject("form", form);
			modelView.setViewName("signUpOTP");
			modelView.addObject("errorMessage", "OTP does not match.");

			return modelView;
		}
		
	}
	
	
	@RequestMapping(value = "/sendOTPAgain",method = RequestMethod.POST)
	public ModelAndView sendOTPAgain(@ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {
		
		ModelAndView modelView = new ModelAndView();
		
		Map<String, String> formMap = form.getMap();
		String username = formMap.get("username");
		Users user = userService.getUserByUserName(username);
		
		userService.updateRowForOTP(user);
		form.getMap().put("email", new String(user.getEmailId()));
		form.getMap().put("OTP", new String(""));
		modelView.addObject("form", form);
		modelView.setViewName("signUpOTP");
		
		return modelView;
		
	}

}

