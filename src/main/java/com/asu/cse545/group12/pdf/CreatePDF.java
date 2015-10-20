package com.asu.cse545.group12.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.asu.cse545.group12.domain.Transactions;
import com.asu.cse545.group12.domain.Users;
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

public class CreatePDF {

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	static List<Transactions> transactions;
	static String accountNumber;
	static Users user;

	/**
	 * @param args
	 */
	public static Document createPDF(String file, List<Transactions> tempTransactions, String tempAccountNumber, Users tempUser) {

		transactions = tempTransactions;
		accountNumber = tempAccountNumber;
		user = tempUser;

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);

			createTable(document);

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

	private static void addMetaData(Document document) {
		document.addTitle("Generate PDF report");
		document.addSubject("Generate PDF report");
		document.addAuthor("Bank of Tempe");
		document.addCreator(user.getFirstName()+" "+user.getLastName());
	}

	private static void addTitlePage(Document document)
			throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("Bank Account Statement", TIME_ROMAN));

		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on "
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		preface.add(new Paragraph("Account Holder: "+ user.getFirstName()+" "+user.getLastName(), TIME_ROMAN_SMALL));
		preface.add(new Paragraph("Account Number: "+ accountNumber, TIME_ROMAN_SMALL));
		preface.add(new Paragraph("Address: "+ user.getAddress()+", City-"+user.getCity()+", State-"+user.getState()+", ZIP-"+user.getZipcode()+", Country-"+user.getCountry(), TIME_ROMAN_SMALL));
		document.add(preface);

	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void createTable(Document document) throws DocumentException {
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

}
