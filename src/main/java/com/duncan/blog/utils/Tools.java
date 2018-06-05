package com.duncan.blog.utils;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 工具类
 *
 */
@SuppressWarnings({ "restriction" })
public class Tools {
	private static final Random RAND = new Random();
	
	public static int rand(int min, int max) {
		return RAND.nextInt(max) % (max - min + 1) + min;
	}
	
	/**
	 * 对称解密
	 * @param data 加密内容 16位
	 * @param key 密钥
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String enAes(String data, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] decValue = cipher.doFinal(data.getBytes());
		return new BASE64Encoder().encode(decValue);
	}

	/**
	 * AES加64加密
	 * @param data 解密内容
	 * @param key 密钥
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String deAes(String data, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] cipherDecodeTypes = new BASE64Decoder().decodeBuffer(data);
		byte[] decValue = cipher.doFinal(cipherDecodeTypes);
		return new String(decValue);
	}
	
	public static boolean isNumber(String str) {
		if (null != str && str.trim().length() != 0 && str.matches("\\d*")) {
			return true;
		}
		return false;
	}
	
}
