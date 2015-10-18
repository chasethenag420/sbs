package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;











import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.TransactionsService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.CreateExternalUserValidator;


@Controller
public class RegularUserController {
	private static final Logger logger = Logger.getLogger(SignUpController.class);

	@Autowired
	UserService userService;
	
	List<Transactions> transactionByAccNum = new ArrayList<Transactions>();

	@Autowired
	TransactionsService transactionService;
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public ModelAndView getProfile(HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("Credit Amount:");
		}
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("profile");
			HttpSession session = request.getSession(false);
			String username=(String)session.getAttribute("username");
			Users user = new Users();
			user=userService.getUserByUserName(username);
			System.out.println(user.getUserName());
			modelView.addObject("user", user);
		
		//validate the input data
		return modelView;
	}
	
	
	
	@RequestMapping(value="/searchTransaction",method = RequestMethod.GET)
	public ModelAndView getTransactions(@ModelAttribute("user") Users user,@ModelAttribute("account") Account account) {
		if(logger.isDebugEnabled()){
			logger.debug("search Transactions:");
		}
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("searchTransaction");
			
			
			transactionByAccNum=transactionService.searchTransactionByInternals(account.getAccountNumber());
			ListIterator<Transactions> it = transactionByAccNum.listIterator();
			while(it.hasNext())
			{
				if(logger.isDebugEnabled()){
					logger.debug(it.next());
				}
			}
			modelView.addObject("transactions", transactionByAccNum);
			return modelView;
		
}
	
}
