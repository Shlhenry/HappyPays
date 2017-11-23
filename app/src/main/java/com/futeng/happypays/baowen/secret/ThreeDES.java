package com.futeng.happypays.baowen.secret;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加密类
 * @author
 */
public class ThreeDES {
	
	public static String ALGORITHM = "DESede/CBC/PKCS5Padding";
	
	public final static byte[] IVBytes;
	
	public final static byte[] keyBytes;

	static {
		String keys = "";
		keyBytes = hex2bin(keys);
		String ivs = "";
		IVBytes = hex2bin(ivs);
	}
	static byte[] hex2bin(String hex) {
		byte[] b = new byte[hex.length() / 2];
		byte[] hb = hex.getBytes();
		byte h, l;
		int n = 0;
		for (int i = 0; i < b.length; i++) {
			h = h2c(hb[i * 2]);
			l = h2c(hb[i * 2 + 1]);
			b[n++] = (byte) (h << 4 | l);
		}
		return b;
	}

	static byte h2c(byte h) {
		if (h >= '0' && h <= '9')
			return (byte) (h - '0');
		if (h >= 'a' && h <= 'f')
			return (byte) (h - 'a' + 10);
		if (h >= 'A' && h <= 'F')
			return (byte) (h - 'A' + 10);
		return -1;
	}

	private static Key key = new SecretKeySpec(keyBytes, "DESede");
	
	private static IvParameterSpec ivParameterSpec = new IvParameterSpec(IVBytes);

	public static byte[] encryptFile(byte[] fileData) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		byte[] encryptData = cipher.doFinal(fileData);
		return encryptData;
	}

	
	/**
	 * 加密
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		byte[] k = key.getEncoded();
		k = ivParameterSpec.getIV();
		byte[] d = cipher.doFinal(src);
		return d;
	}

	
	/**
	 * 解密
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		byte[] k = key.getEncoded();
		k = ivParameterSpec.getIV();
		byte[] d = cipher.doFinal(src);
		return d;
	}
}