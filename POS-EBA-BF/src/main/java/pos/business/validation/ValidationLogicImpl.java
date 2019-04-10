package pos.business.validation;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import pos.business.BusinessContext;
import pos.business.util.CookieHandler;
import pos.business.view.validation.ValidationLogicLocal;
import pos.business.view.validation.ValidationLogicRemote;
import pos.rest.validation.LoginRequest;

@Stateless(name = "ValidationLogic")
@Local(ValidationLogicLocal.class)
@Remote(ValidationLogicRemote.class)
public class ValidationLogicImpl implements ValidationLogicRemote, ValidationLogicLocal {

	@Override
	public String loginRequest(BusinessContext bsCtxt, LoginRequest request) {
		// check if cookie was setted
		if (BusinessContext.isSetted(bsCtxt)) {
			// cookie is setted check if it is valid.
			// check in the database the values from bsCtxt and from bsCtxt.token
			// tell the front end to redirect to index.html

			boolean flag = true;
			if (flag == true) {
				// the information are coorect. Checked the DB
				return bsCtxt.getCookie();
			} else {
				// wrong information.
				return "";
			}

		} else {
			// cookie not setted
			// check the username and password from request in the db
			boolean flag = true;
			if (flag == true) {
				// the username and password match in the db
				String userType = "admin from DB";
				return CookieHandler.createCookie(bsCtxt, request.getEmail(), request.getPassword(), userType);
			} else {
				// the username and password does NOT match in the db
				return "";
			}

		}
	}

}
