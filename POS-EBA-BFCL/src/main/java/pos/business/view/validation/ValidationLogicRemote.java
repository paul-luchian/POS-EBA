package pos.business.view.validation;

import javax.ejb.Remote;

import pos.business.BusinessContext;
import pos.rest.validation.LoginRequest;
import pos.rest.validation.RegisterRequest;

@Remote
public interface ValidationLogicRemote {

	String loginRequest(BusinessContext bsCtxt, LoginRequest request);

	boolean registerRequest(BusinessContext bsCtxt, RegisterRequest request);
}
