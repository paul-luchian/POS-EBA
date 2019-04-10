package pos.business.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import pos.business.BusinessContext;
import pos.util.DateUtility;

public class CookieHandler {
	// cookie valid 7 days
	// 7 days 24h per day 60m per h 60s per m
	// the cookie will have to expire after 7 days from the time when it was setted
	private static final long EXPIRATION_VALUE = 7 * 24 * 60 * 60;
	private static String secretKey = "pos123456789012";
	private static String salt = "sha256posproiect!!";

	public static String encrypt(String strToEncrypt, String secret) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (RuntimeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt, String secret) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (RuntimeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static String createCookie(BusinessContext bsCtxt, String email, String password, String userType) {

		long now = DateUtility.dateToLong(bsCtxt.getRequestTimestamp());
		long expire_date = now + EXPIRATION_VALUE;

		bsCtxt.setToken(generateToken(email, password, userType, now, expire_date));
		bsCtxt.setSettedDate(now);
		bsCtxt.setMail(email);
		bsCtxt.setUserType(userType);
		bsCtxt.setExpirationDate(expire_date);
		return bsCtxt.toString();
	}

	public static String generateToken(String email, String password, String userType, long now, long expire_date) {

		String date = String.valueOf(now);

		String str = email + ";" + password + ";" + userType + ";" + date;
		String token = encrypt(str, secretKey);
		return token;
	}

}
