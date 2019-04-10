package pos.business.util;

import pos.business.BusinessContext;
import pos.util.DateUtility;

public class CookieHandler {
	// cookie valid 7 days
	// 7 days 24h per day 60m per h 60s per m
	// the cookie will have to expire after 7 days from the time when it was setted
	private static final long EXPIRATION_VALUE = 7 * 24 * 60 * 60;

	public static String createCookie(BusinessContext bsCtxt, String email, String password, String userType) {

		bsCtxt.setToken(generateToken(email, password, userType));
		bsCtxt.setSettedDate(DateUtility.dateToLong(bsCtxt.getRequestTimestamp()));
		bsCtxt.setMail(email);
		bsCtxt.setUserType(userType);
		bsCtxt.setExpirationDate(DateUtility.dateToLong(bsCtxt.getRequestTimestamp()) + EXPIRATION_VALUE);
		return bsCtxt.toString();
	}

	private static final String generateToken(String email, String password, String userType) {
		// here to implement the harcoding for the token
		return "tokenHarcodat";
	}

}
