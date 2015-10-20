package com.asu.cse545.group12.controller;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.asu.cse545.group12.dao.AuthorizationDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Authorization;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Transfer;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.AuthorizationService;
import com.asu.cse545.group12.services.TransactionsService;
import com.asu.cse545.group12.services.TransferService;
import com.asu.cse545.group12.services.UserService;
import com.asu.cse545.group12.validator.TransactionInputValidator;



@Controller
public class TransactionController {
	@Autowired 
	TransactionsService transactionservice;

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	TransferService transferService;

	@Autowired
	AuthorizationDao authorizationDao;

	private static final Logger logger = Logger.getLogger(TransactionController.class);


	@RequestMapping(value = "/credit", method = RequestMethod.GET)
	public ModelAndView getCreditForm(HttpServletRequest request) {

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}
		//

		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("credit Screen is executed!");
		}
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("form", new TransactionForm());
		modelView.addObject("accounts", accountNumbers);
		modelView.setViewName("credit");
		return modelView;
	}

	@RequestMapping(value = "creditAmount", method = RequestMethod.POST)
	public ModelAndView creditAmount(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("Credit Amount:");
		}
		TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.validate(form, result);

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", form);
			String username = (String) request.getSession().getAttribute("username");
			Users user = userDao.getUserByUserName(username);
			List<String> accountNumbers = new ArrayList<String>();
			for (Account account : accountService.getAccounts(user.getUserId())) {
				accountNumbers.add(""+account.getAccountNumber());
			}
			
			modelView.addObject("accounts", accountNumbers);
			modelView.setViewName("credit");
			return modelView;
		} else {

			Integer toAccountNumber= Integer.parseInt(form.getToAccount());
			Integer amount= Integer.parseInt(form.getAmount());
			String transactionId = ""+transactionservice.doCredit(toAccountNumber, amount, form.getDescription());
			ModelAndView modelView = new ModelAndView();

			String username = (String) request.getSession().getAttribute("username");
			Users user = userDao.getUserByUserName(username);

			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession().setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);
			
			//create data for OTP page
			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String("credit"));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
			return modelView;


			/*modelView.addObject("accounts", accountNumbers);
			modelView.addObject("successfulMessage", "Successful! The credit request is sent to bank official. Wait for approval.");
			modelView.setViewName("credit");
			modelView.addObject("form", new TransactionForm());
			return modelView;*/
		}

	}

	@RequestMapping(value = "/debit", method = RequestMethod.GET)
	public ModelAndView getDebitForm(HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("Debit Screen is executed!");
		}

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}


		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new TransactionForm());
		modelView.addObject("accounts", accountNumbers);
		modelView.setViewName("debit");
		return modelView;
	}

	@RequestMapping(value = "debitAmount", method = RequestMethod.POST)
	public ModelAndView debitAmount(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {

		if(logger.isDebugEnabled()){
			logger.debug("Debit Amount:");
		}

		TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.validate(form, result);

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			
			String username = (String) request.getSession().getAttribute("username");
			Users user = userDao.getUserByUserName(username);
			List<String> accountNumbers = new ArrayList<String>();
			for (Account account : accountService.getAccounts(user.getUserId())) {
				accountNumbers.add(""+account.getAccountNumber());
			}
			
			modelView.addObject("accounts", accountNumbers);
			
			
			modelView.addObject("form", form);
			modelView.setViewName("debit");
			return modelView;
		} else {

			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			Integer amount= Integer.parseInt(form.getAmount());
			String transactionId = ""+transactionservice.doDebit(fromAccountNumber, amount, form.getDescription());

			ModelAndView modelView = new ModelAndView();

			String username = (String) request.getSession().getAttribute("username");
			Users user = userDao.getUserByUserName(username);

			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession().setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);
			
			//create data for OTP page

			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String("debit"));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
			return modelView;

			

			/*ModelAndView modelView = new ModelAndView();
			modelView.addObject("successfulMessage", "Successful! The debit request is sent to bank official. Wait for approval.");
			modelView.setViewName("debit");
			modelView.addObject("form", new TransactionForm());
			return modelView;*/
		}
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public ModelAndView getTransferForm(HttpServletRequest request) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("transfer Screen is executed!");
		}

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}



		ModelAndView modelView = new ModelAndView();
		modelView.addObject("form", new TransactionForm());
		modelView.addObject("accounts", accountNumbers);
		modelView.setViewName("transfer");
		return modelView;
	}

	@RequestMapping(value = "transferAmount", method = RequestMethod.POST)
	public ModelAndView transferAmount(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("form:"+form.toString());
		}
		//validate the input data

		TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.validate(form, result);

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			
			String username = (String) request.getSession().getAttribute("username");
			Users user = userDao.getUserByUserName(username);
			List<String> accountNumbers = new ArrayList<String>();
			for (Account account : accountService.getAccounts(user.getUserId())) {
				accountNumbers.add(""+account.getAccountNumber());
			}
			
			modelView.addObject("accounts", accountNumbers);
			
			
			modelView.addObject("form", form);
			modelView.setViewName("transfer");
			return modelView;
		} else {

			Integer toAccountNumber= Integer.parseInt(form.getToAccount());
			Integer amount= Integer.parseInt(form.getAmount());
			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			String transactionId = ""+transactionservice.doTransfer(fromAccountNumber, toAccountNumber, amount, form.getDescription());

			
			ModelAndView modelView = new ModelAndView();

			String username = (String) request.getSession().getAttribute("username");
			Users user = userDao.getUserByUserName(username);

			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession().setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);
			
			//create data for OTP page
			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String("transfer"));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
			return modelView;
			

			/*ModelAndView modelView = new ModelAndView();
			if(amount>1000)
				modelView.addObject("successfulMessage", "Successful! The transfer request is sent to bank official. Wait for approval.");
			else
				modelView.addObject("successfulMessage", "Successful! The transfer is done from account: "+fromAccountNumber+" to account: "+toAccountNumber);
			modelView.setViewName("transfer");
			
			modelView.addObject("form", new TransactionForm());
			return modelView;*/
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


	@RequestMapping(value = "enterTransactionOTP")
	public ModelAndView enterSignUpOTP(@ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {

		Map<String, String> formMap = form.getMap();
		String OTP  = formMap.get("OTP");
		String transactionType = formMap.get("transactionType");


		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		String transactionID = (String) request.getSession().getAttribute("transactionID");

		ModelAndView modelView = new ModelAndView();
		
		//System.out.println("***************************************************************** OTP: "+OTP+" username:"+username+ "opt from user: "+user.getOTP());
		if(logger.isDebugEnabled()){
			logger.debug("**********************OTP:"+OTP);
			logger.debug("**********************Usename:"+username);
			logger.debug("**********************OTP from user: "+user.getOTP());
		}
		if(OTP.equals(user.getOTP()))
		{
			if(transactionType.equals("credit") || transactionType.equals("debit"))
			{
				//update transaction
				Transactions transaction = transactionDao.getTransactionByTransactionId(Integer.parseInt(transactionID));
				transaction.setTransactionStatus("pending");
				transactionDao.updateRow(transaction);
				
				//update authorization
				Authorization authorization = authorizationDao.getAuthorizationByTransactionId(Integer.parseInt(transactionID));
				authorization.setRequestStatus("pending");
				authorizationDao.updateRow(authorization);
				if(transactionType.equals("credit"))
					modelView.addObject("successfulMessage", "Successful! The credit request is sent to bank official. Wait for approval.");
				else
					modelView.addObject("successfulMessage", "Successful! The debit request is sent to bank official. Wait for approval.");

			}
			else if(transactionType.equals("transfer"))
			{
				
				//update transaction
				Transactions debitTransaction = transactionDao.getTransactionByTransactionId(Integer.parseInt(transactionID));
				debitTransaction.setTransactionStatus("pending");
				transactionDao.updateRow(debitTransaction);
				
				
				//update transfer
				Transfer transfer = transferService.getTransferByTransferId(debitTransaction.getTransferId());
				transfer.setTransactionStatus("pending");
				transferService.updateRow(transfer);
				
				//update credit transaction
				int creditTransactionId = transfer.getUserToTransactionid();
				Transactions creditTransaction = transactionDao.getTransactionByTransactionId(creditTransactionId);
				creditTransaction.setTransactionStatus("pending");
				transactionDao.updateRow(creditTransaction);
				
				
				//update authorization
				//if amount of debit transaction is > 1000, then only there is authorization in the table
				if(debitTransaction.getAmount() > 1000)
				{
					Authorization authorization = authorizationDao.getAuthorizationByTransactionId(Integer.parseInt(transactionID));
					authorization.setRequestStatus("pending");
					authorizationDao.updateRow(authorization);
				}
				if(debitTransaction.getAmount()>1000)
					modelView.addObject("successfulMessage", "Successful! The transfer request is sent to bank official. Wait for approval.");
				else
					modelView.addObject("successfulMessage", "Successful! The transfer is done from account: "+debitTransaction.getAccountNumber()+" to account: "+creditTransaction.getAccountNumber());
				
			}

			request.getSession().removeAttribute("transactionId");
			modelView.setViewName("successful");
			return modelView;
		}
		else
		{
			modelView = new ModelAndView();
			form.getMap().put("transactionType", transactionType);
			form.getMap().put("email", new String(user.getEmailId()));
			form.getMap().put("OTP", new String(""));
			modelView.addObject("form", form);
			
			modelView.setViewName("transactionOTP");
			modelView.addObject("errorMessage", "OTP does not match.");

			return modelView;
		}

	}


	@RequestMapping(value = "sendTransactionOTPAgain")
	public ModelAndView sendOTPAgain(@ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView();

		Map<String, String> formMap = form.getMap();
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		Users user = userService.getUserByUserName(username);

		transactionservice.sendOTPviaEmail(user);
		form.getMap().put("email", new String(user.getEmailId()));
		form.getMap().put("OTP", new String(""));
		form.getMap().put("transactionType", formMap.get("transactionType"));
		modelView.addObject("form", form);
		modelView.setViewName("transactionOTP");

		return modelView;

	}


}

