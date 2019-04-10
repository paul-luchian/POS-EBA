package pos.business.view.validation;

import javax.ejb.Local;

import pos.business.BusinessContext;
import pos.rest.validation.LoginRequest;

@Local
public interface ValidationLogicLocal extends ValidationLogicRemote {
}
