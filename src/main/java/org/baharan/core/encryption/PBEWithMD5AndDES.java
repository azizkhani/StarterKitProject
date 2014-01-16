package org.baharan.core.encryption;

import java.io.IOException; 
import java.security.GeneralSecurityException;  
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; 
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.PBEKeySpec; 
import javax.crypto.spec.PBEParameterSpec;  
import sun.misc.BASE64Decoder; 
import sun.misc.BASE64Encoder;

public class PBEWithMD5AndDES {
	
	
	private static final char[] PASSWORD = "jalalhoseyniinbaharan".toCharArray();     
	private static final byte[] SALT = { (byte) 0xdd, (byte) 0xee, (byte) 0x30, (byte) 0x82, (byte) 0xce, (byte) 0x22, (byte) 0x10, (byte) 0xdd,     };
	private static final String encrypteAlgorithm="PBEWITHSHA1ANDDESEDE";
	
	public static String encrypt(String property) throws GeneralSecurityException {        
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(encrypteAlgorithm);         
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));         
		Cipher pbeCipher = Cipher.getInstance(encrypteAlgorithm);         
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));         
		return base64Encode(pbeCipher.doFinal(property.getBytes()));     
	}
	
	public static String base64Encode(byte[] bytes) {        
		// NB: This class is internal, and you probably should use another impl         
		return new BASE64Encoder().encode(bytes);  
	}  
	
	public static String decrypt(String property) {
		SecretKeyFactory keyFactory=null;
		String retValue=null;
		try {
			keyFactory = SecretKeyFactory.getInstance(encrypteAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		SecretKey key=null;
		try {
			key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
		Cipher pbeCipher=null;
		try {
			pbeCipher = Cipher.getInstance(encrypteAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		try {
			pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		try {
			 retValue= new String(pbeCipher.doFinal(base64Decode(property)));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	 return retValue;
	}     
	public static byte[] base64Decode(String property) throws IOException {        
		// NB: This class is internal, and you probably should use another impl     
		return new BASE64Decoder().decodeBuffer(property);    
	} 


	public static void main(String[] args) throws Exception {   
		 try {
	            Set<String> algorithms = Security.getAlgorithms("Cipher");
	            for(String algorithm: algorithms) {
	                int max;
	                max = Cipher.getMaxAllowedKeyLength(algorithm);
	                System.out.printf("%-22s: %dbit%n", algorithm, max);
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

			String originalPassword = "postgres";         
			System.out.println("Original password: " + originalPassword);        
			String encryptedPassword = encrypt(originalPassword);         
			System.out.println("Encrypted password: " + encryptedPassword);      
			String decryptedPassword = decrypt(encryptedPassword);         
			System.out.println("Decrypted password: " + decryptedPassword);     
		}      
}
