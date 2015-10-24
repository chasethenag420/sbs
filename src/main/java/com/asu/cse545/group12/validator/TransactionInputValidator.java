package com.asu.cse545.group12.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.AuthorizationServiceImpl;
import com.asu.cse545.group12.services.UserService;

import org.springframework.validation.ValidationUtils;


public class TransactionInputValidator implements Validator{
	
	private static final Logger logger = Logger.getLogger(TransactionInputValidator.class);
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	private HttpServletRequest request;
	
	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean supports(Class aClass) {
		//just validate credit inputs
		return Form.class.isAssignableFrom(aClass);
	}

	public void validate(Object obj, Errors errors) {
		TransactionForm form = (TransactionForm) obj;

		if(form.getTransactionType().equals("credit"))
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Credit Transaction type Identifed***************:" );
			}
			try
			{
				Integer toAccountNumber;
				if("".equals(form.getToAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAccount","not-integer", "Account cannot be empty.");
				else
				{
					toAccountNumber= Integer.parseInt(form.getToAccount());
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					Account account = accountService.getAccount(toAccountNumber);
					if(account == null)
					{
						errors.rejectValue("toAccount", "not-integer", "To Account does not exist.");
					}
					
					if(user.getUserId() != account.getUserId())
					{
						errors.rejectValue("toAccount", "not-integer", "To Account does not belong to you.");
					}
					
					
				}

			}
			catch(Exception e)
			{
				errors.rejectValue("toAccount", "not-integer", "Account Number must be number");
			}

			try
			{
				Integer amount;
				if("".equals(form.getAmount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
				else
					amount= Integer.parseInt(form.getAmount());

			}
			catch(Exception e)
			{
				errors.rejectValue("amount", "not-integer", "Amount must be number");
			}



		}
		else if(form.getTransactionType().equals("debit"))
		{
			if (logger.isDebugEnabled()) {
				logger.debug("****************Debit Transaction type Identifed***************:" );
			}
			try
			{
				Integer fromAccount;
				if("".equals(form.getFromAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromAccount","not-integer", "Account cannot be empty.");
				else
				{
					fromAccount= Integer.parseInt(form.getFromAccount());
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					Account account = accountService.getAccount(fromAccount);
					if(account == null)
					{
						errors.rejectValue("fromAccount", "not-integer", "From Account does not exist.");
					}
					
					if(user.getUserId() != account.getUserId())
					{
						errors.rejectValue("fromAccount", "not-integer", "From Account does not belong to you.");
					}
					
				}

			}
			catch(Exception e)
			{
				errors.rejectValue("fromAccount", "not-integer", "Account Number must be number");
			}

			try
			{
				Integer amount;
				if("".equals(form.getAmount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
				else
					amount= Integer.parseInt(form.getAmount());
			}
			catch(Exception e)
			{
				errors.rejectValue("amount", "not-integer", "Amount must be number");
			}



		}
		else if(form.getTransactionType().equals("transfer"))
		{
			try
			{
				Integer toAccountNumber;
				if("".equals(form.getToAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAccount","not-integer", "Account cannot be empty.");
				else
				{
					toAccountNumber= Integer.parseInt(form.getToAccount());
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					Account account = accountService.getAccount(toAccountNumber);
					if(account == null)
					{
						errors.rejectValue("fromAccount", "not-integer", "To Account does not exist.");
					}
					
				}
			}
			catch(Exception e)
			{
				errors.rejectValue("toAccount", "not-integer", "Account Number must be number");
			}

			try
			{
				Integer fromAccount;
				if("".equals(form.getFromAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromAccount","not-integer", "Account cannot be empty.");
				else
				{
					fromAccount= Integer.parseInt(form.getFromAccount());
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					Account account = accountService.getAccount(fromAccount);
					if(account == null)
					{
						errors.rejectValue("fromAccount", "not-integer", "From Account does not exist.");
					}
					
					if(user.getUserId() != account.getUserId())
					{
						errors.rejectValue("fromAccount", "not-integer", "From Account does not belong to you.");
					}
				}
			}
			catch(Exception e)
			{
				errors.rejectValue("fromAccount", "not-integer", "Account Number must be number");
			}
			try
			{
				Integer amount;
				if("".equals(form.getAmount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
				else
					amount= Integer.parseInt(form.getAmount());
			}
			catch(Exception e)
			{
				errors.rejectValue("amount", "not-integer", "Amount must be number");
			}



		}
		else if(form.getTransactionType().equals("bankStatement"))
		{
			try
			{
				Integer toAccountNumber;
				if("".equals(form.getToAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAccount","not-integer", "Account cannot be empty.");
				else
				{
					toAccountNumber= Integer.parseInt(form.getToAccount());
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					Account account = accountService.getAccount(toAccountNumber);
					if(account == null)
					{
						errors.rejectValue("toAccount", "not-integer", "Account does not exist.");
					}
					
					if(user.getUserId() != account.getUserId())
					{
						errors.rejectValue("toAccount", "not-integer", "Account does not belong to you.");
					}
				}

			}
			catch(Exception e)
			{
				errors.rejectValue("toAccount", "not-integer", "Account Number must be number");
			}

			try
			{
				if("".equals(form.getFromDate()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate","not-Date", "Date cannot be empty");
				else
				{
					SimpleDateFormat format;
					if(form.getFromDate().contains("/"))
					{
						format = new SimpleDateFormat("MM/dd/yyyy");
						Date frmDate = format.parse(form.getFromDate());
					}
					else if(form.getFromDate().contains("-"))
					{
						format = new SimpleDateFormat("yyyy-MM-dd");
						Date frmDate = format.parse(form.getFromDate());
					}
					else
					{
						errors.rejectValue("fromDate", "not-Date", "Illegale Date Format");
					}
				}
			}
			catch(Exception e)
			{
				errors.rejectValue("fromDate", "not-Date", "Illegale Date Format");
			}
			
			try
			{
				if("".equals(form.getToDate()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate","not-Date", "Date cannot be empty");
				else
				{
					SimpleDateFormat format;
					if(form.getToDate().contains("/"))
					{
						format = new SimpleDateFormat("MM/dd/yyyy");
						Date toDate = format.parse(form.getToDate());
					}
					else if(form.getToDate().contains("-"))
					{
						format = new SimpleDateFormat("yyyy-MM-dd");
						Date toDate = format.parse(form.getToDate());
					}	
					else
					{
						errors.rejectValue("toDate", "not-Date", "Illegale Date Format");
					}
				}
			}
			catch(Exception e)
			{
				errors.rejectValue("toDate", "not-Date", "Illegale Date Format");
			}
		}
	}


}

