package pos.rest.validation;

import pos.rest.AbstractRestResponse;

public class LoginResponse extends AbstractRestResponse {

	private static final long serialVersionUID = 1L;

	private String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
