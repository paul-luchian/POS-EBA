package pos.rs.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import pos.business.BusinessContext;
import pos.business.view.validation.ValidationLogicRemote;
import pos.rest.ResponseCodes;
import pos.rest.validation.LoginRequest;
import pos.rest.validation.LoginResponse;
import pos.rest.validation.RegisterRequest;
import pos.rest.validation.RegisterResponse;
import pos.rs.api.ValidationServices;
import pos.util.StringUtility;

@Stateless
public class ValidationServicesImpl implements ValidationServices {
	@Inject
	private ValidationLogicRemote validationLogic;

	@Override
	public LoginResponse loginRequest(HttpServletRequest httpRequest, LoginRequest request) {
		BusinessContext bsCtxt = BusinessContext.from(httpRequest);

		LoginResponse response = new LoginResponse();
		String cookie = validationLogic.loginRequest(bsCtxt, request);
		if (cookie.equals("")) {
			response.setrCode(ResponseCodes.EXCEPTION_THROWN.code);
		}

		response.setCookie(cookie);

		return response;
	}

	@Override
	public Response registerRequest(HttpServletRequest httpRequest, RegisterRequest request) {
		BusinessContext bsCtxt = BusinessContext.from(httpRequest);
		System.out.println(request);
		RegisterResponse response = new RegisterResponse();
		if (validationLogic.registerRequest(bsCtxt, request)) {
			if (BusinessContext.isSetted(bsCtxt)) {
				Response.ok(response).cookie(bsCtxt.getCookie()).status(201).build();
			} else {
				response.setrCode(ResponseCodes.EXCEPTION_THROWN.code);
				Response.ok().status(404).build();
			}
		}
		return Response.ok(response).status(201).build();
	}

}
