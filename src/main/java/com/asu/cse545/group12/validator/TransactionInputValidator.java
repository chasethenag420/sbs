package com.asu.cse545.group12.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.cse545.group12.domain.Form;
import com.asu.cse545.group12.domain.TransactionForm;
import com.asu.cse545.group12.domain.Users;

import org.springframework.validation.ValidationUtils;


public class TransactionInputValidator implements Validator{

	public boolean supports(Class aClass) {
		//just validate credit inputs
		return Form.class.isAssignableFrom(aClass);
	}

	public void validate(Object obj, Errors errors) {
		TransactionForm form = (TransactionForm) obj;

		if(form.getTransactionType().equals("credit"))
		{
			try
			{
				Integer toAccountNumber;
				if("".equals(form.getToAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toAccount","not-integer", "Account cannot be empty.");
				else
					toAccountNumber= Integer.parseInt(form.getToAccount());

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
			try
			{
				Integer fromAccount;
				if("".equals(form.getFromAccount()))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromAccount","not-integer", "Account cannot be empty.");
				else
					fromAccount= Integer.parseInt(form.getFromAccount());

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
					toAccountNumber= Integer.parseInt(form.getToAccount());
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
					fromAccount= Integer.parseInt(form.getFromAccount());
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
					toAccountNumber= Integer.parseInt(form.getToAccount());

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

