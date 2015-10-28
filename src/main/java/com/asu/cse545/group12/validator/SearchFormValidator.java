package com.asu.cse545.group12.validator;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.Searchform;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.UserService;

import org.springframework.validation.ValidationUtils;



public class SearchFormValidator implements Validator{

	private static final Logger logger = Logger.getLogger(SearchFormValidator.class);
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

	@Override
	public boolean supports(Class aClass) {
		//just validate credit inputs
		return Form.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		Searchform form;
		if (obj instanceof Searchform) {
			form = (Searchform) obj;
			try
			{
				Integer toAccountNumber;
				if("".equals(form.getAccountNumber()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber","not-integer", "Account cannot be empty.");
				else
				{
					toAccountNumber=form.getAccountNumber();
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					Account account = accountService.getAccount(toAccountNumber);
					if(account == null)
					{
						errors.rejectValue("accountNumber", "not-integer", "Account does not exist.");
					}

					if(user.getUserId() != account.getUserId())
					{
						errors.rejectValue("accountNumber", "not-integer", "Account does not belong to you.");
					}
				}

			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					logger.debug("****************SearchForm: Exception\n+"+e.getStackTrace() );
				}
				errors.rejectValue("accountNumber", "not-integer", "Account Number must be number");
			
			}
			

			try
			{
				if(form.getFromDate()!= null)
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate","not-Date", "Date cannot be empty");
				else
				{
				
						Date frmDate = form.getFromDate();
					}
			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					logger.debug("****************SearchForm: Exception\n+"+e.getStackTrace() );
				}
				errors.rejectValue("accountNumber", "not-integer", "Account Number must be number");
			
			}

			try
			{
				if(form.getToDate()!= null )
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate","not-Date", "Date cannot be empty");
				else
				{
						Date toDate = form.getToDate();
				}
			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					logger.debug("****************SearchForm: Exception\n+"+e.getStackTrace() );
				}
				errors.rejectValue("accountNumber", "not-integer", "Account Number must be number");
			
			}


		}
	}
}