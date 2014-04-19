package com.ocsoft.oa.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

public class EncryptionUtil
{
	static String baseKey = "@7h$*0m#+-^j59z=";
	static String AES = "AES";
	private static final Logger logger = Logger.getLogger(EncryptionUtil.class);
	
	private static byte[] getUseKeyBytes(String key)
	{
		if(key == null)
			key = "";
		else if(key.length() > 8)
			key = key.substring(0, 8);

		byte[] secondKeyBytes = key.getBytes();
		byte[] baseKeyBytes = baseKey.substring(0, (16 - key.length())).getBytes();
		byte[] useKeyBytes = new byte[secondKeyBytes.length + baseKeyBytes.length];
		System.arraycopy(secondKeyBytes, 0, useKeyBytes, 0, secondKeyBytes.length);
	    System.arraycopy(baseKeyBytes, 0, useKeyBytes, secondKeyBytes.length, baseKeyBytes.length);
	    return useKeyBytes;
	}
	
	public static String encrypt(String str, String key) throws Exception
	{
		return new String(new Hex().encode(cipher(str.getBytes(), key, Cipher.ENCRYPT_MODE)));
	}
	
	public static String decrypt(String str, String key) throws Exception
	{
		return new String(cipher(new Hex().decode(str.getBytes()), key, Cipher.DECRYPT_MODE));
	}
	
	private static byte[] cipher(byte[] bytes, String key, int cipherMode) throws Exception
	{
			byte[] useKeyBytes = getUseKeyBytes(key);
		    
		    SecretKeySpec skeySpec = new SecretKeySpec(useKeyBytes, AES);
		    
		    Cipher cipher = Cipher.getInstance(AES);
		    
		    cipher.init(cipherMode, skeySpec);
		    
		    return cipher.doFinal(bytes);
	}
	
	public static void main(String[] args) throws Exception 
	{
		String key = "VOS1";
		String message = "cochin";
		String encrypted = encrypt(message, key);
		logger.info(encrypted);
		
		String decrypted = decrypt(encrypted, key);
		logger.info(decrypted);
		
	}

}
