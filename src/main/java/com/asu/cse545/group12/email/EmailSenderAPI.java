package com.asu.cse545.group12.email;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderAPI {

	@Autowired
	private MailSender emailSender;

	private String toEmailAddress = "";
	private String fromEmailAddress = "admin@bankoftempe.com";
	private String body = "";
	private String subject = "";

	public EmailSenderAPI(MailSender mailSender) {
		emailSender = mailSender;
	}

	public void setMailSender(MailSender emailSender) {
		this.emailSender = emailSender;
	}



	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}



	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}

	


	public MailSender getEmailSender() {
		return emailSender;
	}



	public void setEmailSender(MailSender emailSender) {
		this.emailSender = emailSender;
	}



	public void sendEmail() {
		 
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setFrom(fromEmailAddress);
		emailMessage.setTo(toEmailAddress);
		emailMessage.setSubject(subject);
		emailMessage.setText(body);
		emailSender.send(emailMessage);
	}

}
