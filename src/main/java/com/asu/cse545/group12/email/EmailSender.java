package com.asu.cse545.group12.email;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.asu.cse545.group12.domain.Users;

public class EmailSender {

	//send the PII attachment to government agency
		public static void sendPIIEmail(Users governmentUser, Users piiUser, FileSystemResource file )
		{

			String configFile = "com/asu/cse545/group12/email/mail-config.xml";
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configFile);

			// @Service("emailService") <-- same annotation you specified in crunchifyEmailAPI.java
			EmailSenderAPI emailAPI = (EmailSenderAPI) context.getBean("emailSenderService");
			String toAddr = governmentUser.getEmailId();

			// email subject
			String subject = "PII Information of "+piiUser.getFirstName()+" "+piiUser.getLastName();
			String attachmentName = ""+piiUser.getFirstName()+"-"+piiUser.getLastName()+"-PII.pdf";
			// email body
			String body = "Dear "+governmentUser.getFirstName()+" "+governmentUser.getLastName()+",\n\n Please find attached PDF. \n Have a good day!";

			emailAPI.setToEmailAddress(toAddr);
			emailAPI.setBody(body);
			emailAPI.setSubject(subject);
			emailAPI.sendEmail(attachmentName, file);
			context.close();
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
			emailAPI.sendEmail();
			context.close();
		}
}
