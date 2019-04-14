package pos.business.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.NewCookie;

import pos.business.BusinessContext;
import pos.util.DateUtility;
import pos.util.RestPaths;

public class CookieHandler {
	// cookie valid 7 days
	// 7 days 24h per day 60m per h 60s per m
	// the cookie will have to expire after 7 days from the time when it was setted
	private static final int EXPIRATION_VALUE = 7 * 24 * 60 * 60 * 1000;
	private static final String SECRET_KEY = "pos123456789012";
	private static final String SALT = "sha256posproiect!!";
	private static final int NO_OF_ELEMENTS_IN_TOKEN = 3;

	public static String[] decryptString(String strToDecrypt) {
		String[] elements = CookieHandler.decrypt(strToDecrypt, SECRET_KEY).split(";");
		if (elements.length != NO_OF_ELEMENTS_IN_TOKEN) {
			return null;
		}
		return elements;
	}

	public static boolean datesValid(long setted_date, long expiry_date) {
		if (setted_date - expiry_date == EXPIRATION_VALUE) {
			return true;
		}
		return false;
	}

	public static String encryptString(String strToEncrypt) {
		return CookieHandler.encrypt(strToEncrypt, SECRET_KEY);
	}

	private static String encrypt(String strToEncrypt, String secret) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
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

	private static String decrypt(String strToDecrypt, String secret) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
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

	public static void createCookie(BusinessContext bsCtxt, String email, String password, String userType) {

		long setted_date = DateUtility.dateToLong(bsCtxt.getRequestTimestamp());
		long expire_date = setted_date + EXPIRATION_VALUE;

		bsCtxt.setToken(generateToken(email, password, setted_date));
		bsCtxt.setMail(email);
		bsCtxt.setExpirationDate(expire_date);
		bsCtxt.setCookie(
				new NewCookie(BusinessContext.COOKIE_NAME, bsCtxt.toString(), RestPaths.PATH_APPLICATION_COOKIE, null,
						1, null, EXPIRATION_VALUE, new Date(bsCtxt.getExpirationDate()), false, false));
	}

	private static String generateToken(String email, String password, long setted_date) {
		String str = email + ";" + password + ";" + setted_date;
		String token = encrypt(str, SECRET_KEY);
		return token;
	}

}
