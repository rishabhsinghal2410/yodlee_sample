/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import yodlee.ysl.api.beans.PublicKeyData;
import yodlee.ysl.api.parsers.GSONParser;

/*
 * PKI Encryption utility
 * 
 * BouncyCastleProvider is used to perform encryption
 * In JDK, the following entry should be added to "java.security" file 
 * security.provider.7=org.bouncycastle.jce.provider.BouncyCastleProvider
 * 
 */
public class EncryptionUtil {
	public static String RSA_ECB_PKCS5 = "RSA/ECB/PKCS1Padding";
	public static final String RSA_CIPHER_TRANSFORMATION = "RSA";
	private static char hex[] = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
	
	/*
	 * This is the PKI encryption logic utility to encrypt the plain text data
	 */
	public static String encrypt(String plainText) {
		String publicKeyJson = getPublicKeyFromFile();
		String encryptedString = null;
		try {
			PublicKeyData keyObj = (PublicKeyData) GSONParser.handleJson(publicKeyJson, yodlee.ysl.api.beans.PublicKeyData.class);
			String keyAsPemString = keyObj.getKeyAsPemString();
			System.out.println(keyAsPemString);
			StringReader fileReader= new StringReader(keyAsPemString);
			PEMReader pemReader= new PEMReader(fileReader);
			PublicKey pk = (PublicKey)pemReader.readObject();
			encryptedString = performDataEncryption(pk, plainText);
			System.out.println("Encrypted String: "+encryptedString);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	
	public static String performDataEncryption(PublicKey publicKey, String cleartext) throws Exception{
		byte ciphertext[] = new byte[0];
		Cipher c = Cipher.getInstance(RSA_ECB_PKCS5);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE, transformKey(publicKey,	    
				RSA_CIPHER_TRANSFORMATION, new BouncyCastleProvider()));	      
		ciphertext = c.doFinal(cleartext.getBytes());
		return encode(ciphertext);
	}
	
	public static String encode(byte[] raw) {
        if (raw == null) return null;
        char[] encoded = new char[raw.length * 2];
        for (int i=0, j=0; i < raw.length; i++) {
            encoded[j++] = hex[(raw[i] >> 4) & 0x0f];
            encoded[j++] = (hex[raw[i] & 0x0f]);
        }
        return new String(encoded);
    }
	
	public static Key transformKey(Key key, String algorithm, Provider provider)
			throws Exception {
		Key ret = null;
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm, provider);
		ret = keyFactory.translateKey(key);
		return ret;
	}
	
	public static String getPublicKeyFromFile() {
		BufferedReader br = null;
		String publicKeyJson = null;
		String temp = null;
		try {
			br = new BufferedReader(new FileReader("PublicKey.txt"));
			while ((temp = br.readLine()) != null) {
				publicKeyJson = temp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return publicKeyJson;
	}
}