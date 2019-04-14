package pos.business.validation;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import pos.business.BusinessContext;
import pos.business.util.CookieHandler;
import pos.business.view.validation.ValidationLogicLocal;
import pos.business.view.validation.ValidationLogicRemote;
import pos.repositories.ProfilesRepositoryImpl;
import pos.repositories.UsersRepositoryImpl;
import pos.repositories.ValidationRepositoryImpl;
import pos.rest.validation.LoginRequest;
import pos.rest.validation.RegisterRequest;
import pos.util.DateUtility;

@Stateless(name = "ValidationLogic")
@Local(ValidationLogicLocal.class)
@Remote(ValidationLogicRemote.class)
public class ValidationLogicImpl implements ValidationLogicRemote, ValidationLogicLocal {

	@EJB(beanName = "ProfilesRepository")
	private ProfilesRepositoryImpl profilesRepository;

	@EJB(beanName = "UsersRepository")
	private UsersRepositoryImpl usersRepository;

	@EJB(beanName = "ValidationRepository")
	private ValidationRepositoryImpl validationRepository;

	@Override
	public String loginRequest(BusinessContext bsCtxt, LoginRequest request) {
		// check if cookie was setted
		/*
		 * if (BusinessContext.isSetted(bsCtxt)) { // cookie is setted check if it is
		 * valid. // check in the database the values from bsCtxt and from bsCtxt.token
		 * // tell the front end to redirect to index.html
		 * 
		 * boolean flag = true; if (flag == true) { // the information are coorect.
		 * Checked the DB return bsCtxt.getCookie(); } else { // wrong information.
		 * return ""; }
		 * 
		 * } else { // cookie not setted // check the username and password from request
		 * in the db boolean flag = true; if (flag == true) { // the username and
		 * password match in the db String userType = "admin from DB"; // return
		 * CookieHandler.createCookie(bsCtxt, request.getEmail(), //
		 * request.getPassword(), userType); } else { // the username and password does
		 * NOT match in the db return ""; }
		 * 
		 * }
		 */
		return null;
	}

	@Override
	public boolean registerRequest(BusinessContext bsCtxt, RegisterRequest request) {
		if (!BusinessContext.isSetted(bsCtxt)) {
			// cookie not setted
			// create user

			usersRepository.insertUser(request.getMail(), request.getPassword());
			profilesRepository.insertProfile(request.getMail(), request.getFirstName(), request.getLastName());
			return true;
		} else {
			// cookie setted

			String[] el = CookieHandler.decryptString(bsCtxt.getToken());
			long setted_date = Long.parseLong(el[2]);
			if (CookieHandler.datesValid(setted_date, bsCtxt.getExpirationDate())) {
				// dates are valid
				if (bsCtxt.getExpirationDate() < DateUtility.dateToLong(bsCtxt.getRequestTimestamp())) {
					// cookie expired
					return false;
				} else {
					// check if cookie is valid
					if (validationRepository.validateToken(el[0], el[1])) {
						// valid cookie
						return true;
					} else {
						// corupted cookie
						return false;
					}
				}
			} else {
				// corupted cookie
				return false;
			}
		}
	}

}
