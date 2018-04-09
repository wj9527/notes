package io.springboot.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
/**
 * 
 * AES
 * @author KevinBlandy
 *
 */
public class AESUtils {
	
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    
    //获取 cipher
    private static Cipher getCipher(byte[] key,int model) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES"); 
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);  
		cipher.init(model, secretKeySpec);
		return cipher;
    }
	
    //AES加密
	public static byte[] encrypt(byte[] data,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
		return cipher.doFinal(data);  
	}
	
	//AES解密
	public static byte[] decrypt(byte[] data,byte[] key) throws InvalidKeyException,  NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
		return cipher.doFinal(data);  
	}
}
