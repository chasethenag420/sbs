package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.services.TransactionsService;



@Controller
public class TransactionController {
	@Autowired 
	TransactionsService transactionservice;

	private static final Logger logger = Logger.getLogger(TransactionController.class);

	
	@RequestMapping(value = "/credit", method = RequestMethod.GET)
	public ModelAndView getCreditForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("credit Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("credit");
		return modelView;
	}
	
	@RequestMapping("creditAmount")
	public ModelAndView creditAmount(@ModelAttribute("form") Form form, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("form:"+form.toString());
		}
		//validate the input data
		Map<String, String> formMap=form.getMap();
		Integer toAccountNumber= Integer.parseInt(formMap.get("toAccountNumber"));
		Integer amount= Integer.parseInt(formMap.get("amount"));
		transactionservice.doCredit(toAccountNumber, amount);
		return new ModelAndView("sample", "form", form);
	}

	@RequestMapping(value = "/debit", method = RequestMethod.GET)
	public ModelAndView getDebitForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Debit Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("debit");
		return modelView;
	}

	@RequestMapping("debitAmount")
	public ModelAndView debitAmount(@ModelAttribute("form") Form form, HttpServletRequest request) {
		return new ModelAndView("sample", "form", form);
	}
	
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public ModelAndView getTransferForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("transfer Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new Form());
		modelView.setViewName("transfer");
		return modelView;
	}
	
	@RequestMapping("transferAmount")
	public ModelAndView transferAmount(@ModelAttribute("form") Form form, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("form:"+form.toString());
		}
		/*//validate the input data
		Map<String, String> formMap=form.getMap();
		Integer toAccountNumber= Integer.parseInt(formMap.get("toAccountNumber"));*/
		return new ModelAndView("sample", "form", form);
	}

}

