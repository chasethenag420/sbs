package com.asu.cse545.group12.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.cse545.group12.dao.AccountDao;
import com.asu.cse545.group12.dao.TransactionDao;
import com.asu.cse545.group12.domain.Account;
import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.AccountService;
import com.asu.cse545.group12.services.AuthorizationServiceImpl;
import com.asu.cse545.group12.services.TransactionsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDFForPIIInformation {

	private  Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private  Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static final Logger logger = Logger.getLogger(CreatePDFForPIIInformation.class);

	Users user;

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	AccountDao accountDao;
	/**
	 * @param args
	 */
	public Document createPDF(String file, Users tempUser) {

		if (logger.isDebugEnabled()) {
			logger.debug("****************createPDF:" );
		}
		
		//transactions = tempTransactions;
		//accountNumber = tempAccountNumber;
		user = tempUser;
		if (logger.isDebugEnabled()) {
			logger.debug("****************UserId : "+user.getUserId() );
		}

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);
			
			createUserTable(document);

			List <Account> accounts= accountDao.getAccountsByUserId(user.getUserId());
			for(Account account: accounts)
			{
				Paragraph preface = new Paragraph();
				creteEmptyLine(preface, 1);
				preface.add(new Paragraph("Account Number : "+account.getAccountNumber(), TIME_ROMAN_SMALL));
				preface.add(new Paragraph("Account Type : "+account.getAccountType(), TIME_ROMAN_SMALL));
				preface.add(new Paragraph("Account Balance : "+account.getBalance(), TIME_ROMAN_SMALL));
				document.add(preface);
				createTransactionTable(document, account);
			}

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return document;

	}

	private  void addMetaData(Document document) {
		document.addTitle("Generate PDF report");
		document.addSubject("Generate PDF report");
		document.addAuthor("Bank of Tempe");
		document.addCreator(user.getFirstName()+" "+user.getLastName());
	}

	private  void addTitlePage(Document document)
			throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("PII Information", TIME_ROMAN));

		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on "
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		preface.add(new Paragraph("Account Holder: "+ user.getFirstName()+" "+user.getLastName(), TIME_ROMAN_SMALL));
		
		document.add(preface);

	}

	private  void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void createTransactionTable(Document document, Account account) throws DocumentException {
		
		String fromDate = account.getCreationDate().toString().substring(0, 10);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
		String currentDate = dateFormat.format(date).toString();
		if (logger.isDebugEnabled()) {
			logger.debug("****************createTransactionTable => Creation Date: "+fromDate+" todaydate: "+currentDate);
		}

		List<Transactions> transactions = transactionDao.getTransactionsBetweenDates(account.getAccountNumber(), fromDate, currentDate);
		
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {1.0f, 1.5f, 3.0f, 1.0f, 1.0f, 1.0f});
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.ORANGE);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("Number", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Transaction Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Transaction Description", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Transaction Type", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Transaction Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Amount", font));
		table.addCell(cell);


		table.setHeaderRows(1);

		for (int i = 0; i < transactions.size(); i++) {

			table.addCell(""+(i+1));
			table.addCell(transactions.get(i).getCreationTimestamp().toString());
			table.addCell(transactions.get(i).getTransactionDescription());
			table.addCell(transactions.get(i).getTransactionType());
			table.addCell(transactions.get(i).getTransactionStatus());
			table.addCell(""+transactions.get(i).getAmount());
		}

		document.add(table);
	}


	private void createUserTable(Document document) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {3.0f, 7.5f});
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.ORANGE);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("Type", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Value", font));
		table.addCell(cell);
		table.setHeaderRows(1);

		table.addCell("First Name");
		table.addCell(user.getFirstName());

		table.addCell("Middle Name");
		table.addCell(user.getMiddleName());

		table.addCell("Last Name");
		table.addCell(user.getLastName());

		table.addCell("Role Id");
		table.addCell(""+user.getRoleId());

		table.addCell("Gender");
		table.addCell(user.getGender());

		table.addCell("User Status");
		table.addCell(user.getUserStatus());

		table.addCell("Registration Date");
		table.addCell(user.getRegistrationDate().toString());

		table.addCell("Last Modified");
		table.addCell(user.getLastModifieddate().toString());

		table.addCell("Phone Number");
		table.addCell(user.getPhoneNumber());

		table.addCell("Email");
		table.addCell(user.getEmailId());

		table.addCell("Address");
		table.addCell(user.getAddress());

		table.addCell("City");
		table.addCell(user.getCity());

		table.addCell("State");
		table.addCell(user.getState());

		table.addCell("Country");
		table.addCell(user.getCountry());

		table.addCell("Zip");
		table.addCell(user.getZipcode());


		table.addCell("Address");
		table.addCell(user.getAddress());


		document.add(table);
	}

}

