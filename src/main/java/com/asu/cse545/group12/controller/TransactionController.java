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



@Controller
public class TransactionController {
	private static final Logger logger = Logger.getLogger(TransactionController.class);

	@RequestMapping( value= "credit")
	public ModelAndView getCredit() {

		logger.debug("Individual controller: getCredit");
		Form form = new Form();
		Map formMap = form.getMap();
		formMap.put("toAccountNumber", "67457745");
		formMap.put("amount", "0");
		formMap.put("transferDescription", "ffffff");
		
		return new ModelAndView("credit", "form", form);
	}
	

	@RequestMapping("creditAmount")
	public ModelAndView creditAmount(HttpServletRequest request) {
		return new ModelAndView("credit");
	}
	
	@RequestMapping(value = "/debit", method = RequestMethod.GET)
	public ModelAndView getDebitForm() {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Debit Screen is executed!");
		}
		return new ModelAndView("debit");
	}

	@RequestMapping("debitAmount")
	public ModelAndView debitAmount(HttpServletRequest request) {
		return new ModelAndView("debit");
	}

}

