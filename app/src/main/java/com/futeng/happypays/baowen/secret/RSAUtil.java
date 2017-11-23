package com.futeng.happypays.baowen.secret;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


/**
 * RSA helper 2.0,it can encrypt and decrypt any length character string<br/>
 * use public key encrypt,use private decrypt<br/>
 * use private key sign,use public key verify<br/>
 * input data was encoded by BASE64<br/>
 * output data use BASE64 encode<br/>
 * 
 * @author never
 * 
 */
public class RSAUtil {

	private static final String CHARSET_NAME = "UTF-8";
	private static final Integer KEY_SIZE = 1024;
	private static final Integer MAX_ENCRYPT_SIZE = 55;
	private static final Integer MAX_DECRYPT_SIZE = 128;


	public static void generateKeyPair(String path) throws IOException {
		KeyPairGenerator keyPairGenerator = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		keyPairGenerator.initialize(KEY_SIZE);

		KeyPair keyPair = keyPairGenerator.genKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		byte[] publicKeyBytes = publicKey.getEncoded();
		byte[] privateKeyBytes = privateKey.getEncoded();

		String publicKeyString = base64Encode(publicKeyBytes);
		String privateKeyString = base64Encode(privateKeyBytes);

		byte[] publicKeyBase64Bytes = publicKeyString.getBytes();
		byte[] privateKeyBase64Bytes = privateKeyString.getBytes();

		File publicKeyFile = new File(path + "public.key");
		File privateKeyFile = new File(path + "private.key");

		FileOutputStream publicKeyFos = new FileOutputStream(publicKeyFile);
		FileOutputStream privateKeyFos = new FileOutputStream(privateKeyFile);

		publicKeyFos.write(publicKeyBase64Bytes);
		privateKeyFos.write(privateKeyBase64Bytes);

		publicKeyFos.flush();
		publicKeyFos.close();
		privateKeyFos.flush();
		privateKeyFos.close();

	}

	/**
	 * encrypt<br/>
	 * result use BASE64 encode
	 * 
	 * @param publicKeyPath
	 * @param needEncrypt
	 * @return
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encrypt(String publicKeyPath, String needEncrypt) throws IOException, InvalidKeySpecException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		PublicKey publicKey = getPublicKey(publicKeyPath);
		return encrypt(publicKey, needEncrypt);
	}

	/**
	 * 
	 * decrypt<br/>
	 * input was BASE64 encode
	 * 
	 * @param privateKeyPath
	 * @param needDecrypt
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decrypt(String privateKeyPath, String needDecrypt) throws InvalidKeySpecException,
			IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		PrivateKey privateKey = getPrivateKey(privateKeyPath);
		return decrypt(privateKey, needDecrypt);
	}

	/**
	 * sign
	 * 
	 * @param privateKeyPath
	 * @param needSign
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String sign(String privateKeyPath, String needSign) throws InvalidKeySpecException, IOException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		PrivateKey privateKey = getPrivateKey(privateKeyPath);
		return encrypt(privateKey, needSign);
	}

	/**
	 * verify
	 * 
	 * @param publicKeyPath
	 * @param needVerify
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String verify(String publicKeyPath, String needVerify) throws InvalidKeySpecException, IOException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		PublicKey publicKey = getPublicKey(publicKeyPath);
		return decrypt(publicKey, needVerify);
	}

	private static String encrypt(Key key, String needEncrypt) throws InvalidKeyException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		if (needEncrypt == null) {
			return null;
		}

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException  e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		// encrypt
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedBytes = new byte[0];
		for (int i = 0; i < needEncrypt.length(); i += MAX_ENCRYPT_SIZE) {
			int start = i;
			int end = needEncrypt.length() < i + MAX_ENCRYPT_SIZE ? needEncrypt.length() : i + MAX_ENCRYPT_SIZE;

			byte[] needEncryptBytes = needEncrypt.substring(start, end).getBytes(CHARSET_NAME);
			byte[] append = cipher.doFinal(needEncryptBytes);

			byte[] newBytes = new byte[encryptedBytes.length + append.length];
			System.arraycopy(encryptedBytes, 0, newBytes, 0, encryptedBytes.length);
			System.arraycopy(append, 0, newBytes, encryptedBytes.length, append.length);

			encryptedBytes = newBytes;
		}

		String encrypted = base64Encode(encryptedBytes);// BASE64 encode
		encrypted = Pattern.compile("\t|\r|\n").matcher(encrypted).replaceAll("");
		return encrypted;
	}

	private static String decrypt(Key key, String needDecrypt) throws IOException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		if (needDecrypt == null) {
			return null;
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		byte[] needDecryptBytes = base64Decode(needDecrypt);// BASE64 decode

		// decrypt
		cipher.init(Cipher.DECRYPT_MODE, key);
		StringBuffer decryptedBuffer = new StringBuffer();
		for (int i = 0; i < needDecryptBytes.length; i += MAX_DECRYPT_SIZE) {
			byte[] append = cipher.doFinal(needDecryptBytes, i, MAX_DECRYPT_SIZE);
			decryptedBuffer.append(new String(append, CHARSET_NAME));
		}

		String decrypted = decryptedBuffer.toString();
		decrypted = Pattern.compile("\t|\r|\n").matcher(decrypted).replaceAll("");
		return decrypted;
	}

	private static PublicKey getPublicKey(String publicKeyPath) throws IOException, InvalidKeySpecException {
		File publicKeyFile = new File(publicKeyPath);
		FileInputStream fis = new FileInputStream(publicKeyFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		int readChar = -1;
		StringBuffer buffer = new StringBuffer();
		while ((readChar = bis.read()) != -1) {
			buffer.append((char) readChar);
		}
		bis.close();

		String publicKeyString = buffer.toString();

		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] publicKeyBytes = base64Decode(publicKeyString);
		KeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		return publicKey;
	}

	private static PrivateKey getPrivateKey(String privateKeyPath) throws IOException, InvalidKeySpecException {
		File privateKeyFile = new File(privateKeyPath);
		FileInputStream fis = new FileInputStream(privateKeyFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		int readChar = -1;
		StringBuffer buffer = new StringBuffer();
		while ((readChar = bis.read()) != -1) {
			buffer.append((char) readChar);
		}
		bis.close();

		String privateKeyString = buffer.toString();

		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] privateKeyBytes = base64Decode(privateKeyString);
		KeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

	/**
	 * BASE64 encode
	 * 
	 * @param needEncode
	 * @return
	 */
	private static String base64Encode(byte[] needEncode) {
		String encoded = null;
		if (needEncode != null) {
			encoded = new BASE64Encoder().encode(needEncode);
		}
		return encoded;
	}

	/**
	 * BASE64 decode
	 * @param needDecode
	 * @return
	 * @throws IOException
	 */
	private static byte[] base64Decode(String needDecode) throws IOException {
		byte[] decoded = null;
		if (needDecode != null) {
			decoded = new BASE64Decoder().decodeBuffer(needDecode);
		}
		return decoded;
	}
	
	
	/**
	 * Main方法
	 * @param args
	 */
	public static void main(String[] args) throws IOException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		// generateKeyPair("");
		String needSign = "我是一个消息，看完就看完了，就没了，当我超过117个字节的时候还能加密吗？我是一个消息，看完就看完了，就没了，当我超过117个字节的时候还能加密吗？我是一个消息，看完就看完了，就没了，当我超过117个字节的时候还能加密吗？";
		String signed = RSAUtil.sign("private.key", needSign);
		String verifyed = RSAUtil.verify("public.key", signed);
		System.out.println("needSign==>" + needSign);
		System.out.println("signed==>" + signed);
		System.out.println("verifyed==>" + verifyed);
	}
}
