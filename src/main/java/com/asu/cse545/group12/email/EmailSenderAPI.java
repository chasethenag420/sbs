package com.asu.cse545.group12.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.asu.cse545.group12.domain.Users;

@Service("emailSenderService")
public class EmailSenderAPI {

	@Autowired
	private JavaMailSenderImpl  emailSender;

	private String toEmailAddress = "";
	private String fromEmailAddress = "admin@bankoftempe.com";
	private String body = "";
	private String subject = "";

	public EmailSenderAPI(JavaMailSenderImpl mailSender) {
		emailSender = mailSender;
	}

	public void setMailSender(JavaMailSenderImpl emailSender) {
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

	


	public JavaMailSenderImpl getEmailSender() {
		return emailSender;
	}



	public void setEmailSender(JavaMailSenderImpl emailSender) {
		this.emailSender = emailSender;
	}



	public void sendEmail() {
		 
		try {
		 MimeMessage mimemessage = emailSender.createMimeMessage();

         
			MimeMessageHelper emailMessage = new MimeMessageHelper(mimemessage, true);
			//SimpleMailMessage emailMessage = new SimpleMailMessage();
			emailMessage.setFrom(fromEmailAddress);
			emailMessage.setTo(toEmailAddress);
			emailMessage.setSubject(subject);
			emailMessage.setText(body);
			//emailSender.send(emailMessage);
			emailSender.send(mimemessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	
	public void sendEmail(String filename, FileSystemResource file) {
		 
		try {
		 MimeMessage mimemessage = emailSender.createMimeMessage();

         
			MimeMessageHelper emailMessage = new MimeMessageHelper(mimemessage, true);
			//SimpleMailMessage emailMessage = new SimpleMailMessage();
			emailMessage.setFrom(fromEmailAddress);
			emailMessage.setTo(toEmailAddress);
			emailMessage.setSubject(subject);
			emailMessage.setText(body);
			//emailSender.send(emailMessage);
			emailMessage.addAttachment(filename, file);
			emailSender.send(mimemessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
