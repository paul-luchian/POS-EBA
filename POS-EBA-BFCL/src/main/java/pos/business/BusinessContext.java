package pos.business;

import java.io.Serializable;
import java.net.HttpCookie;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;

import com.ibm.wsdl.util.StringUtils;

import pos.util.StringUtility;

public class BusinessContext implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String COOKIE_NAME = "server1";

	// fields inside cookie names
	public static final String TOKEN = "Token";
	public static final String EXPIRATION_DATE = "Expiration-date";

	// variables where the fields inside cookies will be putted
	private String token;
	private long expirationDate;
	private final Date requestTimestamp = new Date();
	private NewCookie cookie;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}

	public NewCookie getCookie() {
		return cookie;
	}

	public void setCookie(NewCookie cookie) {
		this.cookie = cookie;
	}

	public Date getRequestTimestamp() {
		return requestTimestamp;
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
		cookie = cookieMap.get(EXPIRATION_DATE);
		if (cookie != null) {
			bsCtxt.setExpirationDate(Long.parseLong(cookie.getValue()));
		}

	}

	public static boolean isSetted(BusinessContext bCtxt) {
		if (!StringUtility.isBlank(bCtxt.getToken()) && bCtxt.getExpirationDate() != 0) {
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

		string.append(EXPIRATION_DATE + "=");
		string.append(this.expirationDate != 0 ? this.expirationDate : 0);

		return string.toString();
	}
}
