package com.asu.cse545.group12.pki;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;


import javax.crypto.Cipher;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.codec.Base64;

import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.pki.PKIConstants.PkiConstants;
import com.asu.cse545.group12.services.UserService;

import sun.security.tools.keytool.CertAndKeyGen ;
import sun.security.x509.BasicConstraintsExtension;
import sun.security.x509.CertificateExtensions;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;



public class CertificateGeneration {


	@Autowired
	UserService  userService;

	private static String keysPath="/home/ubuntu/sbskeys1/allkeys/";

	private List<JavaMailSender> mailSenderList;
	public static String[] attachments= new String[3];
	public static int i=0;
	public String decryptCustomerMessage(Users user, String message){
		String text="";
		try{
			String filePath = keysPath+user.getUserName()+"_"+"PrivateKey.ser";
			PrivateKey privateKey = (PrivateKey)deserializeSecurityObject(filePath);
			text=decrypt(message,privateKey);    	}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return text;    	
	}

	public String ecryptCustomerMessage(Users user, String message){
		String text="";    	
		try{
			String filePath = keysPath+user.getUserName()+"_"+"PublicKey.ser"; 
			PublicKey publicKey = (PublicKey)deserializeSecurityObject(filePath);
			text = encrypt(message,publicKey);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return text; 
	}

	public static String[] certificateGeneration(String userName){
		try{       	
			X509Certificate bankCertificate = (X509Certificate)deserializeSecurityObject(keysPath+"Certificate.ser");      
			CertAndKeyGen keyGen=new CertAndKeyGen("RSA","SHA1WithRSA",null);
			keyGen.generate(1024);
			PrivateKey userPrivateKey=keyGen.getPrivateKey();      
			String username = "CN=" + userName;
			X509Certificate userCertificate = keyGen.getSelfCertificate(new X500Name(username), (long) 365 * 24 * 60 * 60);                                 
			userCertificate   = createSignedCertificate(userCertificate,bankCertificate,userPrivateKey);         
			X509Certificate[] chain = new X509Certificate[1];
			chain[0]=userCertificate; 
			serializeSecurityObject(userName,userCertificate);
			serializeSecurityObject(userName,userPrivateKey);
			serializeSecurityObject(userName,userCertificate.getPublicKey());  
			writeCertificate(userName,userCertificate.toString());

		}catch(Exception ex){

		}
		return attachments;
	}

	private static void serializeSecurityObject(String userName, Object key){
		String fileName="";
		try{
			if(key instanceof PrivateKey){
				fileName = keysPath+userName+"_"+PkiConstants.UserFileExtensionPrivate;
			}
			else if(key instanceof PublicKey){
				fileName = keysPath+userName+"_"+PkiConstants.UserFileExtensionPublic;
			}
			else if(key instanceof X509Certificate){
				fileName = keysPath+userName+"_"+PkiConstants.UserFileExtensionCertificate;
			}            
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(key);
			System.out.println("attachments array"+fileName.toString());
			if(i>2)i=0;
			attachments[i++]=fileName.toString();
			oos.close();            
		}catch(Exception ex){
			ex.printStackTrace();
		}
	} 

	private static X509Certificate createSignedCertificate(X509Certificate certificate,X509Certificate issuerCertificate,PrivateKey issuerPrivateKey){
		try{
			Principal issuer = issuerCertificate.getSubjectDN();
			String issuerSigAlg = issuerCertificate.getSigAlgName();

			byte[] inCertBytes = certificate.getTBSCertificate();
			X509CertInfo info = new X509CertInfo(inCertBytes);
			info.set(X509CertInfo.ISSUER, (X500Name) issuer);              
			if(!certificate.getSubjectDN().getName().equals("CN=BankOfTempe")){
				CertificateExtensions exts=new CertificateExtensions();
				BasicConstraintsExtension bce = new BasicConstraintsExtension(true, -1);
				exts.set(BasicConstraintsExtension.NAME,new BasicConstraintsExtension(false, bce.getExtensionValue()));
				info.set(X509CertInfo.EXTENSIONS, exts);
			}              
			X509CertImpl outCert = new X509CertImpl(info);
			outCert.sign(issuerPrivateKey, issuerSigAlg);

			return outCert;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	private static Object deserializeSecurityObject(String filePath){
		X509Certificate cert = null;
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			try {
				ObjectInputStream input = new ObjectInputStream(fileInputStream);
				if( filePath.contains("Certificate")){
					cert = (X509Certificate)input.readObject();
					return cert;
				}
				else if(filePath.contains("PublicKey")){
					publicKey = (PublicKey)input.readObject();
					return publicKey;
				}
				else if(filePath.contains("PrivateKey")){
					privateKey = (PrivateKey)input.readObject();
					return privateKey;
				}                
			} catch (IOException | ClassNotFoundException e) {                
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {            
			e.printStackTrace();
		}
		return null;
	}

	public  void sendNotificationEmail(Users requester, String attachments[])
	{
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "	bankoftempe@gmail.com";
		String password = "bankoftempe12";
		//String email 
		// message info
		String mailTo = requester.getEmailId();
		String subject = "Account Creation Success - Secure your Key File in a safe location";
		String message = "Dear "+ requester.getFirstName() + ",<br/><br/> Your Account has been created. <br/><br/>Save the attached file at a secure location. This file is needed to decrypt the OTP during login";

		String usageInstr1 = "<br/>1.Download and save the file in some location.(File name will be of the format USERNAME_PrivateKey.ser";
		String usageInstr2 = "<br/>2.While doing Transactions(Credit,Debit,Transfer,Pay Merchant etc) an OTP will be sent in an encrypted Format.";
		String usageInstr3 = "<br/>3.ON the OTP page a downloadable executable(Download Decoder) is present which will be downloaded on clicking it.";
		String usageInstr4 = "<br/>4.Copy paste the encrypted OTP tab and also give full path of the KeyFile along with the KeyFile name";
		String usageInstr5 = "<br/>5.On clicking Decrypt, you will get plain text OTP, use it to validate the OTP";
		String usageInstr6 = "<br/><br/><br/> Regards,<br/>BankofTempe";

		message = message+usageInstr1+usageInstr2+usageInstr3+usageInstr4+usageInstr5+usageInstr6;
		//   String[] attachFiles= new String[2];


		try {
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
					subject, message, attachments);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}


	}



	private void sendEmailWithAttachments(String host, String port,
			final String mailFrom, final String password, String mailTo, String subject,
			String message, String[] attachFiles) throws AddressException, MessagingException, IOException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", mailFrom);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		/*  Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(mailFrom, password);
	            }
	        };*/
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailFrom, password);
			}
		});

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		msg.setFrom(new InternetAddress(mailFrom));
		InternetAddress[] toAddresses = { new InternetAddress(mailTo) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {

				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(message);
				messageBodyPart.setContent(message, "text/html");
				messageBodyPart.attachFile(filePath);
				multipart.addBodyPart(messageBodyPart);
			}

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(message);
			messageBodyPart.setContent(message, "text/html");
			multipart.addBodyPart(messageBodyPart);
			// sets the multi-part as e-mail's content
			msg.setContent(multipart);

			Transport.send(msg);


		}
	}




	private String encrypt(String text, PublicKey key) {
		byte[] cipherText = null;
		String str="";
		try {			
			final Cipher cipher = Cipher.getInstance("RSA");			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			System.out.println(key.toString());
			cipherText = cipher.doFinal(text.getBytes());	
			str =new BigInteger(cipherText).toString();
		} catch (Exception e) {		
		}
		return  base64Encode(str);
	}

	private  String decrypt(String encrypted, PrivateKey key) {
		byte[] dectyptedText = null;
		try {			
			final Cipher cipher = Cipher.getInstance("RSA");			
			cipher.init(Cipher.DECRYPT_MODE, key);	
			encrypted = base64Decode(encrypted);
			BigInteger bigEncrypted = new BigInteger(encrypted);
			byte text [] = bigEncrypted.toByteArray();			
			dectyptedText = cipher.doFinal(text);
			return new String(dectyptedText);
		} catch (Exception ex) {	
			ex.printStackTrace();
		}
		return "INVALID";
	}

	private String base64Encode(String token) {
		byte[] encodedBytes = Base64.encode(token.getBytes());
		return new String(encodedBytes, Charset.forName("UTF-8"));
	}


	private  String base64Decode(String token) {
		byte[] decodedBytes = Base64.decode(token.getBytes());
		return new String(decodedBytes, Charset.forName("UTF-8"));
	}


	private static void writeCertificate(String userName, String certificate){        

		System.out.println(certificate);
		String filename = "G:\\keys\\" + userName + "_Certificate.txt";

		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(certificate);
			bufferedWriter.close();
		}
		catch(IOException ex) {
			System.out.println("Error writing to file '"+ filename + "'");
		}
	}

	private JavaMailSender getJavaMailSender(){
		int index =getRandom(mailSenderList.size());
		return mailSenderList.get(index);
	}

	private int getRandom(int limit){
		Random r = new Random();

		return r.nextInt(limit);
	}
}

