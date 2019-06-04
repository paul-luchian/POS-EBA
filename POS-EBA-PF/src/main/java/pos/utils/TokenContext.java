package pos.utils;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.json.JSONObject;

import pos.business.domains.UserType;
import pos.util.StringUtility;

public class TokenContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private Date generationDate;

	private Date expirationDate;

	private UserType role;

	private String header;

	private String payload;

	private String signature;

	private long userId;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public static TokenContext from(HttpServletRequest request) {

		TokenContext TokenContext = new TokenContext();
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		try {
			token = token.substring(7);
			if (token.length() > 0) {
				TokenContext.setToken(token);
				System.out.println("header=" + TokenContext.getToken());
				breakToken(TokenContext, TokenHandler.decryptToken(token));
			}
		} catch (Exception e) {

		} finally {

		}
		return TokenContext;
	}

	public static void breakToken(TokenContext TokenContext, String parts[]) {
		TokenContext.setHeader(parts[0]);
		TokenContext.setPayload(parts[1]);
		TokenContext.setSignature(parts[2]);
		JSONObject pay = new JSONObject(TokenContext.getPayload());
		TokenContext.setUsername(pay.getString("username"));
		TokenContext.setRole(UserType.valueOf(pay.getString("role")));
		TokenContext.setGenerationDate(new Date(Long.parseLong(String.valueOf(pay.get("create_date")))));
		TokenContext.setUserId(Long.parseLong(String.valueOf(pay.get("userid"))));
		TokenContext.setExpirationDate(new Date(Long.parseLong(String.valueOf(pay.get("exp")))));
	}

	public static boolean isSetted(TokenContext tokenContext) {
		if (!StringUtility.isBlank(tokenContext.getHeader()) && !StringUtility.isBlank(tokenContext.getPayload())
				&& !StringUtility.isBlank(tokenContext.getSignature())
				&& !StringUtility.isBlank(tokenContext.getUsername()) && tokenContext.getExpirationDate().getTime() != 0
				&& tokenContext.getGenerationDate().getTime() != 0 && tokenContext.getRole() != null
				&& tokenContext.getUserId() != 0) {
			return true;
		}
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
