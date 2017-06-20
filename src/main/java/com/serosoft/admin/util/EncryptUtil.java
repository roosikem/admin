package com.serosoft.admin.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;


public class EncryptUtil {
	private static final Logger logger = Logger.getLogger(EncryptUtil.class);
	
	static Cipher ecipher;

	static  Cipher dcipher;
	  EncryptUtil(){
		  
	  }
	  public EncryptUtil(SecretKey key) throws Exception {
	    ecipher = Cipher.getInstance("DES");
	    dcipher = Cipher.getInstance("DES");
	    ecipher.init(Cipher.ENCRYPT_MODE, key);
	    dcipher.init(Cipher.DECRYPT_MODE, key);
	  }

	@SuppressWarnings("restriction")
	public static String encrypt(String str) throws Exception {
	    
	    byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);
        logger.debug(str +"is encrypted");
        return new sun.misc.BASE64Encoder().encode(enc);
	  }

    @SuppressWarnings("restriction")
	public static String decrypt(String str) throws Exception {
	   
	    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte[] utf8 = dcipher.doFinal(dec);
        logger.debug(str +"is decrypted");
        return new String(utf8, "UTF8");
	  }
}
