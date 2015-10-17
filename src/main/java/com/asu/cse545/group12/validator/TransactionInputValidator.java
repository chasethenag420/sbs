package com.asu.cse545.group12.validator;

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
				if(form.getToAccount().equals(""))
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
				if(form.getAmount().equals(""))
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
				if(form.getFromAccount().equals(""))
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
				if(form.getAmount().equals(""))
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
				if(form.getToAccount().equals(""))
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
				if(form.getFromAccount().equals(""))
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
				if(form.getAmount().equals(""))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
				else
					amount= Integer.parseInt(form.getAmount());
			}
			catch(Exception e)
			{
				errors.rejectValue("amount", "not-integer", "Amount must be number");
			}
			
			
			
		}
	}


}

