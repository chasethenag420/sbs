package com.asu.cse545.group12.pki;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.springframework.security.crypto.codec.Base64;

public class Encryption {
/*	public static void main(String[] args)
	
	{
		String decryptedText="sachin";
		String encrypted =doEncrypt(decryptedText, "C:/Users/jaswitha/Downloads/external111PublicKey.ser");
		System.out.println(encrypted);
	}*/
	public static String doEncrypt(String decryptedText, String filePath)
	{
		PublicKey publicKey = null;
		String encryptedOTP = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			try {
				ObjectInputStream input = new ObjectInputStream(fileInputStream);
				if( filePath.contains("PublicKey"))
				{
					publicKey = (PublicKey)input.readObject();
					//publicKey.toString();
					encryptedOTP = encrypt(decryptedText,publicKey);
				}
			}
			catch(Exception inner)
			{
				System.out.println(inner.getMessage());
			}
		}
		catch(Exception outer) {

		}
		return encryptedOTP;
	}

	private static String encrypt(String text, PublicKey key) {
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
	private static String base64Encode(String token) {
		byte[] encodedBytes = Base64.encode(token.getBytes());
		return new String(encodedBytes, Charset.forName("UTF-8"));
	}


	private  String base64Decode(String token) {
		byte[] decodedBytes = Base64.decode(token.getBytes());
		return new String(decodedBytes, Charset.forName("UTF-8"));
	}
}
