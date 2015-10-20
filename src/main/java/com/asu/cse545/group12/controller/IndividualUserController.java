package com.asu.cse545.group12.controller;

import java.util.ArrayList;
import java.util.Date;
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
	/*@RequestMapping( value= "credit")
	public String getCredit( ModelMap map) {

		logger.debug("Individual controller: getCredit");
		
		map.addAttribute("toAccountNumber", 67457745);
		map.addAttribute("amount", 0);
		map.addAttribute("transferDescription", "heloo");
		return "credit";
	}
	*/
	@RequestMapping(value = "/externalsearchtrans", method =RequestMethod.GET)
	public ModelAndView getsearchForm(HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("external user search!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Searchform());
		modelView.setViewName("externalsearchtrans");
		/*HttpSession session = request.getSession(false);
		String username=(String)session.getAttribute("username");
		Users user = userService.getUserByUserName(username);
		Integer userId = user.getUserId();
		
		List<Account> accountlist =accountService.getAccounts(userId);
		Map<String,Integer> accountNumlist =  new LinkedHashMap();
		ListIterator<Account> it = accountlist.listIterator();
		while(it.hasNext())
		{
			Integer accnum=it.next().getAccountNumber();
			accountNumlist.put(accnum.toString(), accnum);
		}
		modelView.addObject("accountlist",accountNumlist);
		*/
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
		return modelView;
	}
}

