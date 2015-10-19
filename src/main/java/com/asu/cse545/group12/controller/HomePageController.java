package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.UserService;

@Controller
public class HomePageController {
	private static final Logger logger = Logger.getLogger(HomePageController.class);
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	@RequestMapping(value = "/individual", method = RequestMethod.GET)
	public ModelAndView getIndividualHomePage() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Home Page requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("individual");

		return modelView;
	}
	
	@RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
	public ModelAndView gotoIndividualHomePage(HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Home Page requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("accountDetails");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		modelView.addObject("accountsRows", accountService.getAccounts(userService.getUserByUserName(username).getUserId()));
		return modelView;
	}
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView getAdminHomePage() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Admin Home Page requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("admin");

		return modelView;
	}
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView getManagerHomePage() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Manager Home Page requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("manager");

		return modelView;
	}
	@RequestMapping(value = "/regular", method = RequestMethod.GET)
	public ModelAndView getRegularHomePage() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Regular Home Page requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("regular");

		return modelView;
	}
	@RequestMapping(value = "/merchant", method = RequestMethod.GET)
	public ModelAndView getMerchantHomePage() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Merchant Home Page requested");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("merchant");

		return modelView;
	}
}
