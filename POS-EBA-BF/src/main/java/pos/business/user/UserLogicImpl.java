package pos.business.user;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import pos.business.BusinessContext;
import pos.business.util.CookieHandler;
import pos.business.view.user.UserLogicLocal;
import pos.business.view.user.UserLogicRemote;
import pos.repositories.UserRepositoryImpl;
import pos.rest.user.PutUserRequest;

@Stateless(name = "UserLogic")
@Local(UserLogicLocal.class)
@Remote(UserLogicRemote.class)
public class UserLogicImpl implements UserLogicRemote, UserLogicLocal {

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepository;

	@Override
	public boolean putUserRequest(BusinessContext bsCtxt, PutUserRequest user) {
		if (!BusinessContext.isSetted(bsCtxt)) {
			// the cookie is not setted
			long id = userRepository.insertUser(user.getUserName(), user.getPassword());
			CookieHandler.createCookie(bsCtxt, user.getUserName(), user.getPassword(), id);
			return true;
		} else {
			// the cookie is setted
			return false;
		}
	}

}
