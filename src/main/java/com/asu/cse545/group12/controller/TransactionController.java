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

import com.asu.cse545.group12.constantfile.Const;
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

	@Autowired
	TransactionInputValidator transactionInputValidator;

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
		//TransactionInputValidator transactionInputValidator = new TransactionInputValidator();
		transactionInputValidator.setRequest(request);
		transactionInputValidator.validate(form, result);

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("form", form);


			modelView.addObject("accounts", accountNumbers);
			modelView.setViewName("credit");
			return modelView;
		} else {

			Integer toAccountNumber = Integer.parseInt(form.getToAccount());
			Double amount =  Double.parseDouble(form.getAmount());

			String transactionId = ""+transactionservice.doCredit(toAccountNumber, amount, form.getDescription());

			if ("-1".equals(transactionId)) {
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("accounts", accountNumbers);
				modelView.addObject("errorMessage", "Transaction service is down, please submit the request again.");
				modelView.addObject("form", form);
				modelView.setViewName("credit");
				return modelView;
			} 
			ModelAndView modelView = new ModelAndView();

			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession().setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);

			//create data for OTP page
			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String(Const.CREDIT_REQUEST));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
			return modelView;

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

		transactionInputValidator.setRequest(request);
		transactionInputValidator.validate(form, result);

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}
		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("accounts", accountNumbers);
			modelView.addObject("form", form);
			modelView.setViewName("debit");
			return modelView;
		} else {

			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			Double amount= Double.parseDouble(form.getAmount());
			String transactionId = ""+transactionservice.doDebit(fromAccountNumber, amount, form.getDescription());

			if ("-1".equals(transactionId)) {
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("accounts", accountNumbers);
				modelView.addObject("errorMessage", "Insufficient balance in the account");
				modelView.addObject("form", form);
				modelView.setViewName("debit");
				return modelView;
			} 
			ModelAndView modelView = new ModelAndView();


			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession(false).setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);

			//create data for OTP page

			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String(Const.DEBIT_REQUEST));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
			return modelView;

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

		transactionInputValidator.setRequest(request);
		transactionInputValidator.validate(form, result);

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}

		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("accounts", accountNumbers);
			modelView.addObject("form", form);
			modelView.setViewName("transfer");
			return modelView;
		} else {

			Integer toAccountNumber= Integer.parseInt(form.getToAccount());
			Double amount= Double.parseDouble(form.getAmount());
			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			String transactionId = ""+transactionservice.doTransfer(fromAccountNumber, toAccountNumber, amount, form.getDescription());

			if ("-1".equals(transactionId)) {
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("accounts", accountNumbers);
				modelView.addObject("errorMessage", "Insufficient balance in the account");
				modelView.addObject("form", form);
				modelView.setViewName("transfer");
				return modelView;
			} 

			ModelAndView modelView = new ModelAndView();

			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession().setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);

			//create data for OTP page
			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String(Const.TRANSFER_REQUEST));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
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
			logger.debug("**********************transactionType: "+transactionType +" and Credit: "+Const.CREDIT_REQUEST);
		}
		if(OTP.matches("[a-zA-Z0-9]{5,10}") && OTP.equals(user.getOTP()))
		{
			if(transactionType.equals(Const.CREDIT_REQUEST) || transactionType.equals(Const.DEBIT_REQUEST))
			{
				//update transaction
				Transactions transaction = transactionDao.getTransactionByTransactionId(Integer.parseInt(transactionID));
				transaction.setTransactionStatus(Const.PENDING);
				transactionDao.updateRow(transaction);

				//update authorization
				Authorization authorization = authorizationDao.getAuthorizationByTransactionId(Integer.parseInt(transactionID));
				authorization.setRequestStatus(Const.PENDING);
				authorizationDao.updateRow(authorization);
				if(transactionType.equals(Const.CREDIT_REQUEST))
					modelView.addObject("successfulMessage", "Successful! The credit request is sent to bank official. Wait for approval.");
				else
					modelView.addObject("successfulMessage", "Successful! The debit request is sent to bank official. Wait for approval.");

			}
			else if(transactionType.equals(Const.TRANSFER_REQUEST) || transactionType.equals(Const.PAY_MERCHANT_REQUEST))
			{

				//update transaction
				Transactions debitTransaction = transactionDao.getTransactionByTransactionId(Integer.parseInt(transactionID));
				debitTransaction.setTransactionStatus(Const.PENDING);
				transactionDao.updateRow(debitTransaction);


				//update transfer
				Transfer transfer = transferService.getTransferByTransferId(debitTransaction.getTransferId());
				transfer.setTransactionStatus(Const.PENDING);
				transferService.updateRow(transfer);

				//update credit transaction
				int creditTransactionId = transfer.getUserToTransactionid();
				Transactions creditTransaction = transactionDao.getTransactionByTransactionId(creditTransactionId);
				creditTransaction.setTransactionStatus(Const.PENDING);
				transactionDao.updateRow(creditTransaction);


				//update authorization
				//if amount of debit transaction is > 1000, then only there is authorization in the table
				if(transactionType.equals(Const.TRANSFER_REQUEST))
				{
					if(debitTransaction.getAmount() > 1000)
					{
						Authorization authorization = authorizationDao.getAuthorizationByTransactionId(Integer.parseInt(transactionID));
						authorization.setRequestStatus(Const.PENDING);
						authorizationDao.updateRow(authorization);

						modelView.addObject("successfulMessage", "Successful! The transfer request is sent to bank official. Wait for approval.");
					}
					else
						modelView.addObject("successfulMessage", "Successful! The transfer is done from account: "+debitTransaction.getAccountNumber()+" to account: "+creditTransaction.getAccountNumber());
				}
				//don't check amount for payMerchant
				else if (transactionType.equals(Const.PAY_MERCHANT_REQUEST))
				{
					Authorization authorization = authorizationDao.getAuthorizationByTransactionId(Integer.parseInt(transactionID));
					authorization.setRequestStatus(Const.PENDING);
					authorizationDao.updateRow(authorization);
					modelView.addObject("successfulMessage", "Successful! The transfer request is sent to bank official. Wait for approval.");
				}


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

	@RequestMapping(value = "payMerchant", method = RequestMethod.GET)
	public ModelAndView getMerchantPayForm(HttpServletRequest request) {
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
		modelView.setViewName("payMerchant");
		return modelView;
	}

	@RequestMapping(value = "submitMerchantPayment", method = RequestMethod.POST)
	public ModelAndView submitMerchantPayment(@Valid @ModelAttribute("form") TransactionForm form, BindingResult result, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("form:"+form.toString());
		}
		//validate the input data

		transactionInputValidator.setRequest(request);
		transactionInputValidator.validate(form, result);

		String username = (String) request.getSession(false).getAttribute("username");
		Users user = userDao.getUserByUserName(username);

		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}


		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("accounts", accountNumbers);
			modelView.addObject("form", form);
			modelView.setViewName("payMerchant");
			return modelView;
		} else {

			Integer toAccountNumber= Integer.parseInt(form.getToAccount());
			Double amount= Double.parseDouble(form.getAmount());
			Integer fromAccountNumber= Integer.parseInt(form.getFromAccount());
			String customerDetails = user.getFirstName()+" "+user.getLastName()+" Account Number: "+fromAccountNumber;
			String transactionId = ""+transactionservice.payMerchant(fromAccountNumber, toAccountNumber, amount, form.getDescription(), customerDetails);

			if ("-1".equals(transactionId)) {
				ModelAndView modelView = new ModelAndView();
				modelView.addObject("accounts", accountNumbers);
				modelView.addObject("errorMessage", "Insufficient balance in the account");
				modelView.addObject("form", form);
				modelView.setViewName("payMerchant");
				return modelView;
			} 
			ModelAndView modelView = new ModelAndView();
			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession().setAttribute("transactionID", transactionId);

			//sent the OTP
			transactionservice.sendOTPviaEmail(user);

			//create data for OTP page
			Form basicform = new Form();
			basicform.getMap().put("email", new String(user.getEmailId()));
			basicform.getMap().put("OTP", new String(""));
			basicform.getMap().put("transactionType", new String(Const.PAY_MERCHANT_REQUEST));

			modelView.addObject("form", basicform);
			modelView.setViewName("transactionOTP");
			return modelView;

		} 

	}


	@RequestMapping(value = "/merchantBulkDebit", method = RequestMethod.GET)
	public ModelAndView merchantBulkDebitForm(HttpServletRequest request) {
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
		modelView.addObject("form", new Form());
		modelView.addObject("accounts", accountNumbers);
		modelView.setViewName("merchantBulkDebit");
		return modelView;
	}

	@RequestMapping(value = "bulkDebitAmount", method = RequestMethod.POST)
	public ModelAndView bulkDebitAmount(@Valid @ModelAttribute("form") Form form, BindingResult result, HttpServletRequest request) {

		if(logger.isDebugEnabled()){
			logger.debug("Merchant bulk Debit Amount:");
		}

		transactionInputValidator.setRequest(request);
		transactionInputValidator.validate(form, result);

		String username = (String) request.getSession().getAttribute("username");
		Users user = userDao.getUserByUserName(username);
		List<String> accountNumbers = new ArrayList<String>();
		for (Account account : accountService.getAccounts(user.getUserId())) {
			accountNumbers.add(""+account.getAccountNumber());
		}
		if (result.hasErrors()) {
			ModelAndView modelView = new ModelAndView();
			modelView.addObject("accounts", accountNumbers);
			modelView.addObject("form", form);
			modelView.setViewName("merchantBulkDebit");
			return modelView;
		} else {


			Map<String, String> formMap = form.getMap();
			String fromAccount  = formMap.get("fromAccount");

			String toAccount1  = formMap.get("toAccount1");
			String amount1  = formMap.get("amount1");
			String description1  = formMap.get("description1");

			String toAccount2  = formMap.get("toAccount2");
			String amount2  = formMap.get("amount2");
			String description2  = formMap.get("description2");

			String toAccount3  = formMap.get("toAccount3");
			String amount3  = formMap.get("amount3");
			String description3  = formMap.get("description3");

			String toAccount4  = formMap.get("toAccount4");
			String amount4  = formMap.get("amount4");
			String description4  = formMap.get("description4");

			String toAccount5  = formMap.get("toAccount5");
			String amount5  = formMap.get("amount5");
			String description5  = formMap.get("description5");


			List<String> toAccountList = new ArrayList<String>();
			List<String> amountList = new ArrayList<String>();
			List<String> descriptionList = new ArrayList<String>();

			toAccountList.add(toAccount1);
			toAccountList.add(toAccount2);
			toAccountList.add(toAccount3);
			toAccountList.add(toAccount4);
			toAccountList.add(toAccount5);

			amountList.add(amount1);
			amountList.add(amount2);
			amountList.add(amount3);
			amountList.add(amount4);
			amountList.add(amount5);

			descriptionList.add(description1);
			descriptionList.add(description2);
			descriptionList.add(description3);
			descriptionList.add(description4);
			descriptionList.add(description5);


			Integer fromAccountNumber= Integer.parseInt(fromAccount);
			String transactionId="";
			int emptyToAccountCount = 0;
			for(int i=0; i<5; i++)
			{
				if("".equals(toAccountList.get(i)))
				{
					emptyToAccountCount++;
					continue;
				}
				if(emptyToAccountCount == 5)
				{
					ModelAndView modelView = new ModelAndView();
					modelView.addObject("accounts", accountNumbers);
					modelView.addObject("errorMessage", "Enter at least one To Account");
					modelView.addObject("form", form);
					modelView.setViewName("merchantBulkDebit");
					return modelView;
				}
				Integer toAccountNumber= Integer.parseInt(toAccountList.get(i));
				Double amount= Double.parseDouble(amountList.get(i));
				String newTransactionId = ""+transactionservice.doTransfer(fromAccountNumber, toAccountNumber, amount, descriptionList.get(i));

				if ("-1".equals(newTransactionId)) {
					ModelAndView modelView = new ModelAndView();
					modelView.addObject("accounts", accountNumbers);
					modelView.addObject("errorMessage", "Insufficient balance in the account");
					modelView.addObject("form", form);
					modelView.setViewName("merchantBulkDebit");
					return modelView;
				}
				transactionId = transactionId+newTransactionId;
			}
			ModelAndView modelView = new ModelAndView();


			//save the transaction id in the session so that we can use it in later in OTP page
			request.getSession(false).setAttribute("transactionID", transactionId);

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

		}
	}

}

