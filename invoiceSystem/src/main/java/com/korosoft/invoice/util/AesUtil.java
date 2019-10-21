package com.korosoft.invoice.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//加密解密的工具类
public class AesUtil {

	private static final String PASSWORD = "123456";

	public static byte[] encrypt(String content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(password.getBytes());
		kgen.init(128, random);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(1, key);
		byte[] result = cipher.doFinal(byteContent);
		return result;
	}

	public static byte[] decrypt(byte[] content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(password.getBytes());
		kgen.init(128, random);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(2, key);
		byte[] result = cipher.doFinal(content);
		return result;
	}

	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; ++i) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	
	public static String encrypt(String content) throws Exception {
		byte[] enResult = encrypt(content, PASSWORD);
		String hexStr = parseByte2HexStr(enResult);
		String baseStr = Base64.getBase64(hexStr);
		return baseStr;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content) throws Exception {
		String enResult = Base64.getFromBase64(content);
		byte[] bt = parseHexStr2Byte(enResult);
		byte[] resultBt = decrypt(bt, PASSWORD);
		String result = new String(resultBt, "utf-8");
		return result;
	}

}
