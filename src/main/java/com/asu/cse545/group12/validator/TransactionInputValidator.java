package com.asu.cse545.group12.validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.cse545.group12.constantfile.Const;
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
		String typeOfForm = "";
		TransactionForm form = null;
		Form basicForm = null;
		if (obj instanceof TransactionForm) {
			form = (TransactionForm) obj;
			typeOfForm = "TransactionForm";
		}
		else if (obj instanceof Form) {
			basicForm = (Form) obj;
			typeOfForm = "BasicForm";
		}

		if("TransactionForm".equalsIgnoreCase(typeOfForm))
		{
			if(form.getTransactionType().equalsIgnoreCase(Const.CREDIT_REQUEST))
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
					if (logger.isDebugEnabled()) {
						logger.debug("****************Credit Transaction : Exception\n+"+e.getStackTrace() );
					}
					errors.rejectValue("toAccount", "not-integer", "Account Number must be number");
				}

				try
				{
					Double amount;
					if("".equals(form.getAmount()))
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
					else
					{
						amount= Double.parseDouble(form.getAmount());
						if(! (amount>0.0))
						{
							errors.rejectValue("amount","not-integer", "Amount cannot be 0 or less than 0");
						}
					}

				}
				catch(Exception e)
				{
					errors.rejectValue("amount", "not-integer", "Amount must be number");
				}



			}
			else if(form.getTransactionType().equalsIgnoreCase(Const.DEBIT_REQUEST))
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
						if (logger.isDebugEnabled()) {
							logger.debug("****************Debit Transaction : Testing "+form.getFromAccount() );
						}
						fromAccount= Integer.parseInt(form.getFromAccount());
						if (logger.isDebugEnabled()) {
							logger.debug("****************Debit Transaction : Testing "+fromAccount );
						}
						logger.debug("****************Debit Transaction : User: "+userService );
						Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
						logger.debug("****************Debit Transaction : User+"+user );
						Account account = accountService.getAccount(fromAccount);

						if (logger.isDebugEnabled()) {
							logger.debug("****************Debit Transaction : account+"+account.toString() );
						}
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
					if (logger.isDebugEnabled()) {
						e.printStackTrace();
						logger.debug("****************Debit Transaction : Exception\n+" );
					}
					errors.rejectValue("fromAccount", "not-integer", "Account Number must be number");
				}

				try
				{
					Double amount;
					if("".equals(form.getAmount()))
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
					else
					{
						amount= Double.parseDouble(form.getAmount());
						if(! (amount>0.0))
						{
							errors.rejectValue("amount","not-integer", "Amount cannot be 0 or less than 0");
						}
					}
				}
				catch(Exception e)
				{
					errors.rejectValue("amount", "not-integer", "Amount must be number");
				}



			}
			else if(form.getTransactionType().equalsIgnoreCase(Const.TRANSFER_REQUEST))
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
							errors.rejectValue("toAccount", "not-integer", "To Account does not exist.");
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
					Double amount;
					if("".equals(form.getAmount()))
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
					else
					{
						amount= Double.parseDouble(form.getAmount());
						if(! (amount>0.0))
						{
							errors.rejectValue("amount","not-integer", "Amount cannot be 0 or less than 0");
						}
					}
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
						if(!(form.getFromDate().length()== 10 && form.getFromDate().contains("/") && form.getFromDate().matches("[0-9//]+")))
						{
							if (logger.isDebugEnabled()) {
								logger.debug("****************SearchForm: Illegale date format\n+"+ form.getFromDate());
							}
							errors.rejectValue("fromDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
						}
						else
						{
							String [] dateParts = form.getFromDate().split("/");
							if(dateParts.length == 3)
							{
								Integer month = Integer.parseInt(dateParts[0]);
								Integer day = Integer.parseInt(dateParts[1]);
								Integer year = Integer.parseInt(dateParts[2]);
								if(month>0 && month<13 && day>0 && day<32 && year>1990)
								{
									if (logger.isDebugEnabled()) {
										for(String a: dateParts)
											logger.debug("****************SearchForm: date partst+"+ a);
									}
									SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
									Date frmDate = format.parse(form.getFromDate());

								}
								else
								{
									errors.rejectValue("fromDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
								}
							}
							else
							{
								errors.rejectValue("fromDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
							}
						}
					}
				}
				catch(Exception e)
				{
					errors.rejectValue("fromDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
				}

				try
				{
					if("".equals(form.getToDate()))
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate","not-Date", "Date cannot be empty");
					else
					{
						if(!(form.getToDate().length()== 10 && form.getToDate().contains("/") && form.getToDate().matches("[0-9//]+")))
						{
							if (logger.isDebugEnabled()) {
								logger.debug("****************SearchForm: Illegale date format\n+"+ form.getToDate());
							}
							errors.rejectValue("toDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
						}
						else
						{
							String [] dateParts = form.getToDate().split("/");
							if(dateParts.length == 3)
							{
								Integer month = Integer.parseInt(dateParts[0]);
								Integer day = Integer.parseInt(dateParts[1]);
								Integer year = Integer.parseInt(dateParts[2]);
								if(month>0 && month<13 && day>0 && day<32 && year>1990)
								{
									if (logger.isDebugEnabled()) {
										for(String a: dateParts)
											logger.debug("****************SearchForm: date partst+"+ a);
									}

									SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
									Date frmDate = format.parse(form.getToDate());

								}
								else
								{
									errors.rejectValue("toDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
								}
							}
							else
							{
								errors.rejectValue("toDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
							}
						}
					}
				}
				catch(Exception e)
				{
					errors.rejectValue("toDate", "not-Date", "Illegale Date Format (MM/dd/yyyy)");
				}
			}

			else if(form.getTransactionType().equalsIgnoreCase(Const.PAY_MERCHANT_REQUEST))
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
							errors.rejectValue("toAccount", "not-integer", "To Account does not exist.");
						}
						else
						{
							int userId = account.getUserId();
							Users toUser = userService.getUserByUserId(userId);
							if(toUser != null && toUser.getRoleId() != 2)
							{
								errors.rejectValue("toAccount", "not-integer", "To Account does not belong to any Merchant.");
							}
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
					Double amount;
					if("".equals(form.getAmount()))
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount","not-integer", "Amount cannot be empty");
					else
					{
						amount= Double.parseDouble(form.getAmount());
						if(! (amount>0.0))
						{
							errors.rejectValue("amount","not-integer", "Amount cannot be 0 or less than 0");
						}
					}
				}
				catch(Exception e)
				{
					errors.rejectValue("amount", "not-integer", "Amount must be number");
				}



			}
		}
		else if("BasicForm".equalsIgnoreCase(typeOfForm))
		{
			Map<String, String> formMap = basicForm.getMap();
			String fromAccount1  = formMap.get("fromAccount");

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

			//validate from account
			try
			{
				if (logger.isDebugEnabled()) {
					logger.debug("*************** Merchant Bulk Payment validation trace  : From account1: "+fromAccount1 );
					logger.debug("*************** Merchant Bulk Payment validation trace  : To account1: "+toAccount1 );
					logger.debug("*************** Merchant Bulk Payment validation trace  : amount: "+amount1 );
				}
				Integer fromAccount;
				if("".equals(fromAccount1))
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map['fromAccount']","not-integer", "Account cannot be empty.");
				else
				{
					if (logger.isDebugEnabled()) {
						logger.debug("****************Debit Transaction : Testing " );
					}
					fromAccount= Integer.parseInt(fromAccount1);
					if (logger.isDebugEnabled()) {
						logger.debug("****************Debit Transaction : Testing "+fromAccount );
					}
					logger.debug("****************Debit Transaction : User: "+userService );
					Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
					logger.debug("****************Debit Transaction : User+"+user );
					Account account = accountService.getAccount(fromAccount);

					if (logger.isDebugEnabled()) {
						logger.debug("****************Debit Transaction : account+"+account.toString() );
					}
					if(account == null)
					{
						errors.rejectValue("map['fromAccount']", "not-integer", "From Account does not exist.");
					}

					if(user.getUserId() != account.getUserId())
					{
						errors.rejectValue("map['fromAccount']", "not-integer", "From Account does not belong to you.");
					}

				}

			}
			catch(Exception e)
			{
				if (logger.isDebugEnabled()) {
					e.printStackTrace();
					logger.debug("****************Debit Transaction : Exception\n+" );
				}
				errors.rejectValue("map['fromAccount']", "not-integer", "Account Number must be number");
			}

			for(int i=0; i<5; i++)
			{
				try
				{
					Integer toAccountNumber;
					if("".equals(toAccountList.get(i)))
					{
						if(!("".equalsIgnoreCase(amountList.get(i))))
						{
							errors.rejectValue("map['amount"+(i+1)+"']", "not-integer", "Remove amount as Account number is empty.");
						}
						if(!("".equalsIgnoreCase(descriptionList.get(i))))
						{
							errors.rejectValue("map['description"+(i+1)+"']", "not-integer", "Remove Description as Account number is empty.");
						}
					}
					else
					{
						toAccountNumber= Integer.parseInt(toAccountList.get(i));
						Users user = userService.getUserByUserName((String)request.getSession(false).getAttribute("username"));
						Account account = accountService.getAccount(toAccountNumber);
						if(account == null)
						{
							errors.rejectValue("map['toAccount"+(i+1)+"']", "not-integer", "To Account does not exist.");
						}


					}

				}
				catch(Exception e)
				{
					if (logger.isDebugEnabled()) {
						logger.debug("****************Credit Transaction : Exception\n+"+e.getStackTrace() );
					}
					errors.rejectValue("map['toAccount"+(i+1)+"']", "not-integer", "Account Number must be number");
				}

				try
				{
					Double amount;
					if("".equals(toAccountList.get(i)))
					{
						//don't do validation of amount if to account is empty
					}
					else
					{
						if("".equals(amountList.get(i)))
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map['amount"+(i+1)+"']","not-integer", "Amount cannot be empty");
						else
						{
							amount= Double.parseDouble(amountList.get(i));
							if(! (amount>0.0))
							{
								errors.rejectValue("map['amount"+(i+1)+"']","not-integer", "Amount cannot be 0 or less than 0");
							}
						}
					}

				}
				catch(Exception e)
				{
					errors.rejectValue("map['amount"+(i+1)+"']", "not-integer", "Amount must be number");
				}
			}
		}
	}


}

