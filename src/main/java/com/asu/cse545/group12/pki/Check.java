package com.asu.cse545.group12.pki;

public class Check {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CertificateGeneration certGen = new CertificateGeneration();
		String[] attachments = new String[3];
		
		attachments=certGen.certificateGeneration("srikanth");
		
		certGen.sendNotificationEmail("srikanth", attachments);
	}

}
