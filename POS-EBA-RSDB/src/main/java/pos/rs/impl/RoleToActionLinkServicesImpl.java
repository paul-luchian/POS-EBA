package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.business.domains.ActionType;
import pos.business.domains.UserType;
import pos.dtos.RoleToActionLinkDto;
import pos.repositories.RoleToActionLinkRepositoryImpl;
import pos.rs.api.RoleToActionLinkServices;

@Stateless
public class RoleToActionLinkServicesImpl implements RoleToActionLinkServices {

	@EJB(beanName = "RoleToActionLinkRepository")
	private RoleToActionLinkRepositoryImpl roleToActionLinkRepo;

	@Override
	public String storeRoleToActionLinkRequest(HttpServletRequest httpRequest, RoleToActionLinkDto dto) {
		long id = roleToActionLinkRepo.insertRoleToActionLink(dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<RoleToActionLinkDto> getRoleToActionLinkRequest(HttpServletRequest httpRequest, UserType userType,
			ActionType actionType, String uri) {
		return roleToActionLinkRepo.selectRoleToActionLinks(userType, actionType, uri);
	}

	@Override
	public RoleToActionLinkDto getRoleToActionLinkByIdRequest(HttpServletRequest httpRequest, long linkId) {
		return roleToActionLinkRepo.selectRoleToActionLInkDtoById(linkId);
	}

	@Override
	public String updateRoleToActionLinkRequest(HttpServletRequest httpRequest, RoleToActionLinkDto dto, long linkId) {
		long id = roleToActionLinkRepo.updateRoleToActionLink(linkId, dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public void deleteRoleToActionLinkRequest(HttpServletRequest httpRequest, long linkId) {
		roleToActionLinkRepo.deleteRoleToActionLink(linkId);
	}

}
