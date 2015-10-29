package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.asu.cse545.group12.constantfile.Const;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Searchform;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.TransactionsService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;
import com.asu.cse545.group12.validator.SearchFormValidator;
import com.asu.cse545.group12.validator.TransactionInputValidator;


@Controller
public class IndividualUserController {
	private static final Logger logger = Logger.getLogger(IndividualUserController.class);


	@Autowired
	TransactionsService transactionService;

	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	
	@Autowired
	SearchFormValidator searchformValidator;
	@Autowired
	AuthorizationService authorizationService;
	
	@Autowired
	TransactionDao transactionDao;

	@RequestMapping(value = "/externalsearchtrans")
	public ModelAndView getsearchForm(HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("external user search!");
		}

		String username = (String) request.getSession().getAttribute("username");
		if(username == null || username.equals(""))
		{
			ModelAndView modelView = new ModelAndView();
			//modelView.addObject("form", new Form());
			modelView.addObject("error", "User does not exit. Enter valid username");

			modelView.setViewName("login");
			return modelView;
		}
		Users user = userService.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}
			
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Searchform());
		modelView.addObject("accounts",accountNumbers);
		modelView.setViewName("externalsearchtrans");

		return modelView;
	}

	@RequestMapping(value="externalsearchtransform")
	public ModelAndView externalsearchtransform(@Valid @ModelAttribute("form") Searchform searchform, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("External User search:");
		}
		ModelAndView modelView = new ModelAndView();
		
		//validate the input data
		searchformValidator.setRequest(request);
		searchformValidator.validate(searchform, result);
		
		
		String username = (String) request.getSession().getAttribute("username");
		if(username == null || username.equals(""))
		{
			//ModelAndView modelView = new ModelAndView();
			//modelView.addObject("form", new Form());
			modelView.addObject("error", "User does not exit. Enter valid username");

			modelView.setViewName("login");
			return modelView;
		}
		else{
			Users user = userService.getUserByUserName(username);
			List<String> accountNumbers = new ArrayList<String>();
			for (Account account : accountService.getAccounts(user.getUserId())) {
				accountNumbers.add(""+account.getAccountNumber());
			}
			if (result.hasErrors()) {
				modelView.addObject("form", searchform);
				   modelView.addObject("accounts", accountNumbers);
				modelView.setViewName("externalsearchtrans");
				return modelView;
			}
	
			Integer accountNum=searchform.getAccountNumber();
			List<Transactions> transactions = transactionDao.getTransactionsBetweenDates(searchform.getAccountNumber(), searchform.getFromDate(), searchform.getToDate());
			//transactionsList=transactionService.searchTransactionByExternals(accountNum, toDate, fromDate);
			modelView.addObject("transactions", transactions);
			modelView.addObject("accounts", accountNumbers);
			modelView.addObject("form", new Searchform());
			modelView.setViewName("externalsearchtrans");
			Iterator it =transactions.listIterator();
			while(it.hasNext())
			{
				System.out.println("transactionlist"+it.next());
			}
			return modelView;
		}
	}
	
	
	@RequestMapping(value="raiseExternalRequest")
	public ModelAndView raiseExternalRequest(@ModelAttribute("form") Form form, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.addObject("authorization", new Authorization());
		model.setViewName("raiseExternalRequest");
		return model;
	}
	
	
	@RequestMapping(value = "externalrequest")
	public ModelAndView getInteralEmplRequest(@ModelAttribute("authorization") Authorization authorization,HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("create request");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("message", "You are not allowed to raise request");

		HttpSession session = request.getSession(false);
		String requesterusername=(String) session.getAttribute("username");
		if(requesterusername == null || requesterusername.equals(""))
		{
			//ModelAndView modelView = new ModelAndView();
			//modelView.addObject("form", new Form());
			modelView.addObject("error", "User does not exit. Enter valid username");

			modelView.setViewName("login");
			return modelView;
		}
		else{
			try{
				
	
				
				Users requesteruser = userService.getUserByUserName(requesterusername);
				int requesteruserid = requesteruser.getUserId();
				if(logger.isDebugEnabled()){
					logger.debug("requesteruserid: "+requesteruserid);
				}
				//set the request to Manager and if the regular has necessary permission he can see it also
				authorization.setAssignedToRole(4);;
				authorization.setAuthorizedToUserId(requesteruserid);
	
				authorization.setRequestStatus(Const.PENDING);
				authorization.setRequestCreationTimeStamp(Calendar.getInstance().getTime());
				authorizationService.regularEmpRequest(authorization);	
				modelView.addObject("message", "Request created successfully");
				modelView.setViewName(getViewName(requesterusername));
				// need to write the message that request was successful.
			}catch(Exception e){
				e.printStackTrace();
				modelView.setViewName(getViewName(requesterusername));
			}
			return modelView;
		}

	}
	
	
	private String getViewName(String username){

		int roleId = userService.getUserByUserName(username).getRoleId();
		// for individual and Merchant user
		if (roleId == 1) {
			return "individual";
		} else if (roleId == 2) {
			return "merchant";
		} else
			return "404";
	}


}

