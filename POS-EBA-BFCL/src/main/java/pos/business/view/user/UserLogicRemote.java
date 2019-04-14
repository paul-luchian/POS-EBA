package pos.business.view.user;

import javax.ejb.Remote;

import pos.business.BusinessContext;
import pos.rest.user.PutUserRequest;

@Remote
public interface UserLogicRemote {
	boolean putUserRequest(BusinessContext bsCtxt, PutUserRequest user);
}
