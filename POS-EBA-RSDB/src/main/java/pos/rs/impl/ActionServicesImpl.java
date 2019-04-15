package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.business.domains.ActionType;
import pos.entities.Action;
import pos.repositories.ActionRepositoryImpl;
import pos.rs.api.ActionServices;
import pos.util.StringUtility;

@Stateless
public class ActionServicesImpl implements ActionServices {

	@EJB(beanName = "ActionRepository")
	private ActionRepositoryImpl actionRepository;

	@Override
	public String actionRequest(HttpServletRequest httpRequest, Action action) {
		long id = actionRepository.insertAction(action.getType(), action.getUri());
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<Action> getActionsRequest(HttpServletRequest httpRequest) {
		return actionRepository.selectActions();
	}

	@Override
	public Action getActionRequest(HttpServletRequest httpRequest, long actionId) {
		return actionRepository.selectActionById(actionId);
	}

	@Override
	public List<Action> getActionRequest(HttpServletRequest httpRequest, ActionType type, String uri) {
		if (type != null && !StringUtility.isBlank(uri)) {
			return actionRepository.selectActionsByUriAndType(uri, type);
		}
		if (type != null) {
			return actionRepository.selectActionsByType(type);
		}
		if (!StringUtility.isBlank(uri)) {
			return actionRepository.selectActionsByUri(uri);
		}
		return null;
	}

	@Override
	public void deleteActionRequest(HttpServletRequest httpRequest, long actionId) {
		actionRepository.deleteActionById(actionId);

	}

}