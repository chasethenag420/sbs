package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.services.TransactionsService;
import com.asu.cse545.group12.validator.TransactionInputValidator;



@Controller
public class TransactionController {
	@Autowired 
	TransactionsService transactionservice;

	@Autowired
	UserDao userDao;
	
	private static final Logger logger = Logger.getLogger(TransactionController.class);


	@RequestMapping(value = "/credit", method = RequestMethod.GET)
	public ModelAndView getCreditForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("credit Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new TransactionForm());
		modelView.setViewName("credit");
		return modelView;
	}

	@RequestMapping(value = "creditAmount")
	public ModelAndView creditAmount(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("Credit Amount:");
		}
		TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.validate(form, result);

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", form);
			modelView.setViewName("credit");
			return modelView;
		} else {

			Integer toAccountNumber= Integer.parseInt(form.getToAccount());
			Integer amount= Integer.parseInt(form.getAmount());
			transactionservice.doCredit(toAccountNumber, amount);
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("successfulMessage", "Successful! The credit request is sent to bank official. Wait for approval.");
			modelView.setViewName("credit");
			modelView.addObject("form", new TransactionForm());
			return modelView;
		}

	}

	@RequestMapping(value = "/debit", method = RequestMethod.GET)
	public ModelAndView getDebitForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Debit Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new TransactionForm());
		modelView.setViewName("debit");
		return modelView;
	}

	@RequestMapping(value = "debitAmount")
	public ModelAndView debitAmount(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {

		if(logger.isDebugEnabled()){
			logger.debug("Debit Amount:");
		}

		TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.validate(form, result);

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", form);
			modelView.setViewName("debit");
			return modelView;
		} else {

			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			Integer amount= Integer.parseInt(form.getAmount());
			transactionservice.doDebit(fromAccountNumber, amount);
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("successfulMessage", "Successful! The debit request is sent to bank official. Wait for approval.");
			modelView.setViewName("debit");
			modelView.addObject("form", new TransactionForm());
			return modelView;
		}
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public ModelAndView getTransferForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("transfer Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new TransactionForm());
		modelView.setViewName("transfer");
		return modelView;
	}

	@RequestMapping(value = "transferAmount")
	public ModelAndView transferAmount(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("form:"+form.toString());
		}
		//validate the input data

		TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.validate(form, result);
		
		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", form);
			modelView.setViewName("transfer");
			return modelView;
		} else {
			
			Integer toAccountNumber= Integer.parseInt(form.getToAccount());
			Integer amount= Integer.parseInt(form.getAmount());
			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			transactionservice.doTransfer(fromAccountNumber, toAccountNumber, amount);
			ModelAndView modelView = new ModelAndView();
			if(amount>1000)
				modelView.addObject("successfulMessage", "Successful! The transfer request is sent to bank official. Wait for approval.");
			else
				modelView.addObject("successfulMessage", "Successful! The transfer is done from account: "+fromAccountNumber+" to account: "+toAccountNumber);
			modelView.setViewName("transfer");
			modelView.addObject("form", new TransactionForm());
			return modelView;
		} 
		
	}
	
	@RequestMapping(value = "goBack")
	public ModelAndView goBack(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("moving to back!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(getViewName(username));
		return modelView;
	}

	
	private String getViewName(String username){
		
		int roleId = userDao.getUserByUserName(username).getRoleId();
		// for individual user
		if (roleId == 1) {
			return "individual";
		} else if (roleId == 2) {
			return "merchant";
		} else if (roleId == 3) {
			return "regular";
		} else if (roleId == 4) {
			return "manager";
		} else if (roleId == 5) {
			return "admin";
		} else
			return "404";
	}

}

