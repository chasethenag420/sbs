package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Searchform;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.TransactionsService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class IndividualUserController {
	private static final Logger logger = Logger.getLogger(IndividualUserController.class);


	@Autowired
	TransactionsService transactionService;

	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	List<Transactions> transactionsList = new ArrayList<Transactions>();
	
	@RequestMapping(value = "/externalsearchtrans")
	public ModelAndView getsearchForm(HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("external user search!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Searchform());
		modelView.setViewName("externalsearchtrans");

		return modelView;
	}

	@RequestMapping(value="externalsearchtransform")
	public ModelAndView creditAmount(@ModelAttribute("searchform") Searchform searchform) {
		if(logger.isDebugEnabled()){
			logger.debug("External User search:");
		}
		ModelAndView modelView = new ModelAndView();
		//validate the input data
		Integer accountNum=searchform.getAccountNumber();
		Date toDate = searchform.getToDate();
		Date fromDate = searchform.getFromDate();
		transactionsList=transactionService.searchTransactionByExternals(accountNum, toDate, fromDate);
		modelView.addObject("transactions", transactionsList);
		modelView.addObject("form", new Searchform());
		modelView.setViewName("externalsearchtrans");
		Iterator it =transactionsList.listIterator();
		while(it.hasNext())
		{
			System.out.println("transactionlist"+it.next());
		}
		return modelView;
	}

	@RequestMapping(value="addAccount")
	public ModelAndView addAccount() {
		if(logger.isDebugEnabled()){
			logger.debug("adding saving account to user account");
		}

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("addAccount");
		return modelView;
	}

	@RequestMapping(value="addUserAccount")
	public ModelAndView addUserAccount(HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("adding saving account to user account");
		}
		Users user = userService.getUserByUserName((String) request.getSession(false).getAttribute("username"));
		ModelAndView modelView = new ModelAndView();
		if(user == null)
		{
			modelView.addObject("errorMessage", "User does not exist. Login again");
			modelView.setViewName("login");
			return modelView;
		}

		modelView.addObject("successfulMessage", "Your request of creating Saving Account is forwarded to bank official.\n Wait for 5-6 business to activate the account.");
		modelView.setViewName("successful");
		return modelView;
	}
	
	
}

