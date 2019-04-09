package pos.rs.impl;

import javax.servlet.http.HttpServletRequest;

import pos.rest.ResponseCodes;
import pos.rest.validation.LoginRequest;
import pos.rest.validation.LoginResponse;
import pos.rest.validation.RegisterRequest;
import pos.rest.validation.RegisterResponse;
import pos.rs.api.ValidationServices;

public class ValidationServicesImpl implements ValidationServices {

	@Override
	public LoginResponse loginRequest(HttpServletRequest httpRequest, LoginRequest request) {
		System.out.println("##################################"+request);
		LoginResponse response = new LoginResponse();
		response.setCookie("cookie");
		response.setrCode(ResponseCodes.OK.code);
		return response;
	}

	@Override
	public RegisterResponse registerRequest(HttpServletRequest httpRequest, RegisterRequest request) {
		System.out.println("##################################"+request);
		RegisterResponse response = new RegisterResponse();
		response.setCookie("cookie");
		response.setrCode(ResponseCodes.OK.code);
		return response;
	}

}
