package pos.rs.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import pos.business.BusinessContext;
import pos.business.view.user.UserLogicRemote;
import pos.rest.user.PutUserRequest;
import pos.rs.api.UserServices;

@Stateless
public class UserServicesImpl implements UserServices {

	@Inject
	private UserLogicRemote userLogicRemote;

	@Override
	public Response putUserRequest(HttpServletRequest httpRequest, PutUserRequest user) {
		BusinessContext bsCtxt = BusinessContext.from(httpRequest);
		System.out.println(user);
		if (userLogicRemote.putUserRequest(bsCtxt, user)) {
			// userul a fost creat si cookie-ul setat
			if (BusinessContext.isSetted(bsCtxt)) {
				return Response.ok().cookie(bsCtxt.getCookie()).status(201).build();
			} else {
				return Response.status(401).build();
			}
		} else {
			// cookie existent
			// send error code
			return Response.status(401).build();
		}
	}

}
