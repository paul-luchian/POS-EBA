package pos.business;

import java.io.Serializable;
import java.net.HttpCookie;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import com.ibm.wsdl.util.StringUtils;

import pos.util.StringUtility;

public class BusinessContext implements Serializable {

	private static final long serialVersionUID = 1L;

	// fields inside cookie names
	public static final String TOKEN = "Token";
	public static final String MAIL = "Mail";
	public static final String USER_TYPE = "User-type";
	public static final String EXPIRATION_DATE = "Expiration-date";
	public static final String SETTED_DATE = "Setted-date";

	// variables where the fields inside cookies will be putted
	private String token;
	private String mail;
	private String userType;
	private long expirationDate;
	private long settedDate;
	private String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}

	public long getSettedDate() {
		return settedDate;
	}

	public void setSettedDate(long settedDate) {
		this.settedDate = settedDate;
	}

	public Date getRequestTimestamp() {
		return requestTimestamp;
	}

	private final Date requestTimestamp = new Date();

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public static BusinessContext from(HttpServletRequest request) {
		BusinessContext businessContext = new BusinessContext();

		String cookies = request.getHeader(HttpHeaders.COOKIE);
		breakCookie(businessContext, parseCookies(cookies));
		return businessContext;
	}

	public static Map<String, HttpCookie> parseCookies(String cookies) {
		Map<String, HttpCookie> cookieMap = new LinkedHashMap<>();
		if (cookies != null) {
			for (String cookie : cookies.split(";")) {
				if (!StringUtility.isBlank(cookie)) {
					List<HttpCookie> cookieList = HttpCookie.parse(cookie);
					for (HttpCookie httpCookie : cookieList) {
						cookieMap.put(httpCookie.getName(), httpCookie);
					}
				}
			}
		}
		return cookieMap;
	}

	public static void breakCookie(BusinessContext bsCtxt, Map<String, HttpCookie> cookieMap) {
		HttpCookie cookie = cookieMap.get(TOKEN);
		if (cookie != null) {
			bsCtxt.setToken(cookie.getValue());
		}
		cookie = cookieMap.get(MAIL);
		if (cookie != null) {
			bsCtxt.setMail(cookie.getValue());
		}
		cookie = cookieMap.get(USER_TYPE);
		if (cookie != null) {
			bsCtxt.setUserType(cookie.getValue());
		}
		cookie = cookieMap.get(EXPIRATION_DATE);
		if (cookie != null) {
			bsCtxt.setExpirationDate(Long.parseLong(cookie.getValue()));
		}
		cookie = cookieMap.get(SETTED_DATE);
		if (cookie != null) {
			bsCtxt.setSettedDate(Long.parseLong(cookie.getValue()));
		}
		bsCtxt.setCookie(bsCtxt.toString());
	}

	public static boolean isSetted(BusinessContext bCtxt) {
		if (!StringUtility.isBlank(bCtxt.getMail()) && !StringUtility.isBlank(bCtxt.getToken())
				&& !StringUtility.isBlank(bCtxt.getUserType()) && bCtxt.getExpirationDate() != 0
				&& bCtxt.getSettedDate() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();

		string.append(TOKEN + "=");
		string.append(this.token != null ? this.token : "");
		string.append(";");

		string.append(MAIL + "=");
		string.append(this.mail != null ? this.mail : "");
		string.append(";");

		string.append(USER_TYPE + "=");
		string.append(this.userType != null ? this.userType : "");
		string.append(";");

		string.append(EXPIRATION_DATE + "=");
		string.append(this.expirationDate != 0 ? this.expirationDate : 0);
		string.append(";");

		string.append(SETTED_DATE + "=");
		string.append(this.settedDate != 0 ? this.settedDate : 0);
		this.setCookie(string.toString());
		return string.toString();
	}
}
