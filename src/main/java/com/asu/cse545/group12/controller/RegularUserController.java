package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.util.Date;
import java.security.Principal;
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

import com.asu.cse545.group12.dao.SecurityQuestionsDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.SecurityQuestions;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.UserPII;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.TransactionsService;
import com.asu.cse545.group12.services.UserService;


@Controller
public class RegularUserController {
	private static final Logger logger = Logger.getLogger(SignUpController.class);

	@Autowired
	UserService userService;

	@Autowired
	AuthorizationService authorizationService;

	List<Transactions> transactionByAccNum = new ArrayList<Transactions>();

	@Autowired
	TransactionsService transactionService;
	
	@Autowired
	SecurityQuestionsDao securityQuestionsDao;

	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public ModelAndView getProfile(HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("profile:");
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
	
	@RequestMapping(value="/searchTransaction")
	public ModelAndView setTransactions() {
		if(logger.isDebugEnabled()){
			logger.debug("search Transactions:");
		}
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("searchTransaction");
			modelView.addObject("form", new Form());
			modelView.addObject("account", new Account());
			modelView.addObject("user", new Users());
			return modelView;
	}
	
	
	
	
	@RequestMapping(value="/searchTransactionform")
	public ModelAndView getTransactions(@ModelAttribute("user") Users user,@ModelAttribute("account") Account account) {
		if(logger.isDebugEnabled()){
			logger.debug("search Transactions:");
		}
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("searchTransaction");
			modelView.addObject("form", new Form());
			System.out.println("accountnum"+account.getAccountNumber());
			System.out.println("username"+user.getFirstName());
			transactionByAccNum=transactionService.searchTransactionByInternals(account.getAccountNumber());
			System.out.println("transactions list"+transactionByAccNum);
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
	
	@RequestMapping(value="/deleteTransaction" ,method = RequestMethod.POST)
	public ModelAndView approveTransactions(@ModelAttribute("form") Form form) {
		if(logger.isDebugEnabled()){
			logger.debug("aprove transactions:");
		}
		ModelAndView model = new ModelAndView();
		model.addObject("form", new Form());
		model.addObject("user", new Users());
		model.addObject("account", new Account());
		model.setViewName("searchTransaction");
		Map<String, String> formMap = form.getMap();
		Integer transactionId = Integer.parseInt(formMap.get("transactionId"));
		System.out.println("delete transaction"+formMap.get("transactionId"));
		int result =transactionService.deleteTransaction(transactionId);
		System.out.println("result"+result);
		if(result ==1)
		{
			model.addObject("msg", "successfully deleted the transaction");
		}
	return model;
	}
	
	
	

	@RequestMapping(value = "/regularEmprequest", method = RequestMethod.GET)
	public ModelAndView setInteralEmplRequest(/*@ModelAttribute("authorization") Authorization authorization ,@ModelAttribute("user") Users user*/) {
		if(logger.isDebugEnabled()){
			logger.debug("create request");
		}
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("user", new Users());
			modelView.addObject("authorization", new Authorization());
			modelView.setViewName("regularEmprequest");
			return modelView;
		
}
	
	@RequestMapping(value = "regularrequest")
	public ModelAndView getInteralEmplRequest(@ModelAttribute("authorization") Authorization authorization ,@ModelAttribute("user") Users user,HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("create request");
		}
			ModelAndView modelView = new ModelAndView();
			
			modelView.setViewName("regularEmprequest");
			Users usermain = userService.getUserByUserName(user.getUserName());
			//System.out.println(usermain);
			int userId = usermain.getUserId();
			HttpSession session = request.getSession(false);
			String reuqesterusername=(String) session.getAttribute("username");
			Users requesteruser = userService.getUserByUserName(reuqesterusername);
			int requesteruserid = requesteruser.getUserId();
			System.out.println("requesteruserid"+requesteruserid);
			System.out.println("requestedto"+userId);
			authorization.setAuthorizedByUserId(userId);
			authorization.setAuthorizedToUserId(requesteruserid);
			
			authorization.setRequestStatus("pending");
			authorizationService.regularEmpRequest(authorization);	
			// need to write the message that request was successful.
			return modelView;
		
}
	
	@RequestMapping(value = "enterSecurityQuestions", method = RequestMethod.POST)
	public ModelAndView enterSecurityQuestions(HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("enter security questions");
		}
	
		
		List<String> questions = new ArrayList<String>();
		questions.add("What is the name of the place your wedding reception was held?");
		questions.add("What was the name of your elementary / primary school?");
		questions.add("In what city or town does your nearest sibling live?");
		questions.add("What time of the day were you born? (hh:mm)");
		questions.add("What was the house number and street name you lived in as a child?");
		questions.add("What were the last four digits of your childhood telephone number?");
		questions.add("In what town or city was your first full time job?");
		questions.add("What are the last five digits of your driver's licence number?");
		questions.add("What is your grandmother's (on your mother's side) maiden name?");
		questions.add("What is your spouse or partner's mother's maiden name?");
		questions.add("In what town or city did your mother and father meet?");
		
		Form form = new Form();
		form.getMapObject().put("question1", questions);
		form.getMapObject().put("question2", questions);
		form.getMapObject().put("question3", questions);
		
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", form);
		modelView.setViewName("setSecurityQuestions");
		
		return modelView;

	}

	@RequestMapping(value = "addSecurityQuestions", method = RequestMethod.POST)
	public ModelAndView addSecurityQuestions(@ModelAttribute("form") Form form, HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding security questions");
		}
		ModelAndView modelView = new ModelAndView();

		Map<String, String> map = form.getMap();
		
		List<String> questions = new ArrayList<String>();
		questions.add("What is the name of the place your wedding reception was held?");
		questions.add("What was the name of your elementary / primary school?");
		questions.add("In what city or town does your nearest sibling live?");
		questions.add("What time of the day were you born? (hh:mm)");
		questions.add("What was the house number and street name you lived in as a child?");
		questions.add("What were the last four digits of your childhood telephone number?");
		questions.add("In what town or city was your first full time job?");
		questions.add("What are the last five digits of your driver's licence number?");
		questions.add("What is your grandmother's (on your mother's side) maiden name?");
		questions.add("What is your spouse or partner's mother's maiden name?");
		questions.add("In what town or city did your mother and father meet?");
		
		
		if((map.get("question1").equals(map.get("question2")) || map.get("question2").equals(map.get("question3")) || map.get("question1").equals(map.get("question3"))) && (map.get("answer1").equals("") || map.get("answer2").equals("") || map.get("answer3").equals("")))
		{
			
			Form newForm = new Form();
			newForm.getMapObject().put("question1", questions);
			newForm.getMapObject().put("question2", questions);
			newForm.getMapObject().put("question3", questions);
			modelView.addObject("errorMessage", "Every question must be different and answer should not be same for different questions.");
			modelView.addObject("form", newForm);
			modelView.setViewName("setSecurityQuestions");
			return modelView;
		}
		
		String username = (String) request.getSession(false).getAttribute("username");
		Users user = userService.getUserByUserName(username);
		if(user != null)
		{
			SecurityQuestions securityQuestions = new SecurityQuestions();
			securityQuestions.setUserId(user.getUserId());
			securityQuestions.setQuestion1(map.get("question1"));
			securityQuestions.setAnswer1(map.get("answer1"));
			securityQuestions.setQuestion2(map.get("question2"));
			securityQuestions.setAnswer2(map.get("answer2"));
			securityQuestions.setQuestion3(map.get("question3"));
			securityQuestions.setAnswer3(map.get("answer3"));
			
			securityQuestionsDao.insertRow(securityQuestions);
			
			Form newForm = new Form();
			newForm.getMapObject().put("question1", questions);
			newForm.getMapObject().put("question2", questions);
			newForm.getMapObject().put("question3", questions);
			modelView.addObject("", "Every question must be different and answer should not be same for different questions.");
			
			modelView.addObject("form", newForm);
			modelView.addObject("successfulMessage", "Successfull! Security Questions are set Successfully.");
			modelView.setViewName("setSecurityQuestions");
			
			return modelView;
			
		}
		modelView.setViewName("login");
		return modelView;
		
		
	}
	
	@RequestMapping(value = "/viewExternalprofile")
	public ModelAndView viewExternalprofile() {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding security questions");
		}
		ModelAndView model = new ModelAndView();
		
		model.addObject("form", new Form());
		model.setViewName("viewExternalprofile");
		return model;
	}
	
	@RequestMapping(value = "/viewExternalprofileform")
	public ModelAndView getExternalprofile(@ModelAttribute("form") Form form, HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding security questions");
		}
			ModelAndView model = new ModelAndView();
			HttpSession session = request.getSession(false);
			String requesterusername=(String) session.getAttribute("username");
			Map samplemap = form.getMap();
			String requestedtousername= (String) samplemap.get("userName");
			
			Users requestfromuser = userService.getUserByUserName(requesterusername);
			int requesterUserId=requestfromuser.getUserId();
			Users requesttouser = userService.getUserByUserName(requestedtousername);
			int requesttoUserId=requesttouser.getUserId();
			
			List<Authorization> authorizationList = authorizationService.getAuthorizedNotifications(requesterUserId, requesttoUserId);
			Authorization finalrequest = null;
			Users user= new Users();
			if(authorizationList!=null){
				finalrequest = authorizationList.get(0);
				 user = userService.getUserByUserName(requestedtousername);
				finalrequest.setRequestStatus("INACTIVE");
				authorizationService.update(finalrequest);
			}
			model.addObject("user",user);
			model.setViewName("profile");
		return model;
	}
	
	
	
}
