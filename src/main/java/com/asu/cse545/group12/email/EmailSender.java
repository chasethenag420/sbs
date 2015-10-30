package com.asu.cse545.group12.email;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.pki.CertificateGeneration;

public class EmailSender {

	//send the PII attachment to government agency
		public static void sendPIIEmail(Users governmentUser, Users piiUser, String [] attachments )
		{

			

			String host = "smtp.gmail.com";
			String port = "587";
			String mailFrom = "	bankoftempe@gmail.com";
			String password = "bankoftempe12";
			//String email 
			// message info
			String mailTo = governmentUser.getEmailId();
			String subject = "PII Information of "+piiUser.getFirstName()+" "+piiUser.getLastName();
			String message = "Dear "+governmentUser.getFirstName()+" "+governmentUser.getLastName()+",<br/><br/> Please find attached PDF. <br/> Have a good day!";

			

			try {
				CertificateGeneration certificateGeneration = new CertificateGeneration();
				certificateGeneration.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
						subject, message, attachments);
				System.out.println("Email sent.");
			} catch (Exception ex) {
				System.out.println("Could not send email.");
				ex.printStackTrace();
			}
		}


		//send email without the PII attachment to government agency
		
		public static void sendPIIEmail(Users user, String message)
		{

			String configFile = "com/asu/cse545/group12/email/mail-config.xml";
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configFile);

			// @Service("emailService") <-- same annotation you specified in crunchifyEmailAPI.java
			EmailSenderAPI emailAPI = (EmailSenderAPI) context.getBean("emailSenderService");
			String toAddr = user.getEmailId();

			// email subject
			String subject = "PII Information Request";

			// email body
			String body = "Dear "+user.getFirstName()+" "+user.getLastName()+",\n\n "+message+" \n Have a good day!";

			emailAPI.setToEmailAddress(toAddr);
			emailAPI.setBody(body);
			emailAPI.setSubject(subject);
			System.out.println("******hello");
			emailAPI.sendEmail();
			System.out.println("*****bye");
			context.close();
		}
}
