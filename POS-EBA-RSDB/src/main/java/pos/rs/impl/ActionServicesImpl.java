package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.business.domains.ActionType;
import pos.dtos.ActionDto;
import pos.repositories.ActionRepositoryImpl;
import pos.rs.api.ActionServices;

@Stateless
public class ActionServicesImpl implements ActionServices {

	@EJB(beanName = "ActionRepository")
	private ActionRepositoryImpl actionRepo;

	@Override
	public String storeActionRequest(HttpServletRequest httpRequest, ActionDto dto) {
		long id = actionRepo.insertAction(dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<ActionDto> getActionsRequest(HttpServletRequest httpRequest, ActionType type, String uri) {
		return actionRepo.selectActions(type, uri);
	}

	@Override
	public ActionDto getActionRequest(HttpServletRequest httpRequest, long actionId) {
		return actionRepo.selectActionDtoById(actionId);
	}

	@Override
	public String updateActionRequest(HttpServletRequest httpRequest, ActionDto dto, long actionId) {
		long id = actionRepo.updateAction(actionId, dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public void deleteActionRequest(HttpServletRequest httpRequest, long actionId) {
		actionRepo.deleteAction(actionId);

	}

}