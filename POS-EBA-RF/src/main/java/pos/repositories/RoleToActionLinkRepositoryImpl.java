package pos.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pos.PersistenceManager;
import pos.business.domains.ActionType;
import pos.business.domains.UserType;
import pos.dtos.RoleToActionLinkDto;
import pos.entities.Action;
import pos.entities.Action_;
import pos.entities.Role;
import pos.entities.RoleToActionLink;
import pos.entities.RoleToActionLink_;
import pos.entities.Role_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "RoleToActionLinkRepository")
@LocalBean
public class RoleToActionLinkRepositoryImpl extends PersistenceManager {

	@EJB
	ActionRepositoryImpl actionRepo;

	@EJB
	RoleRepositoryImpl roleRepo;

	public long insertRoleToActionLink(RoleToActionLinkDto dto) {
		PosValidationException exc = new PosValidationException();

		Action action = actionRepo.selectActionById(dto.getActionId());
		if (action == null) {
			exc.add(new ValidationHint("actionId", "Action not found!"));
		}
		Role role = roleRepo.selectRoleById(dto.getRoleId());
		if (role == null) {
			exc.add(new ValidationHint("roleId", "Role not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		RoleToActionLink link = new RoleToActionLink();
		link.setAction(action);
		link.setRole(role);
		getEntityManager().persist(link);
		return link.getId();
	}

	public List<RoleToActionLinkDto> selectRoleToActionLinks(UserType userType, ActionType actionType, String uri) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RoleToActionLink> query = builder.createQuery(RoleToActionLink.class);
		Root<RoleToActionLink> root = query.from(RoleToActionLink.class);

		List<Predicate> predicates = new ArrayList<>();
		if (userType != null) {
			predicates.add(builder.equal(root.get(RoleToActionLink_.ROLE).get(Role_.TYPE), userType));
		}
		if (actionType != null) {
			predicates.add(builder.equal(root.get(RoleToActionLink_.ACTION).get(Action_.TYPE), actionType));
		}
		if (!StringUtility.isBlank(uri)) {
			Expression<String> exp = builder.trim(' ', builder.lower(root.get(RoleToActionLink_.ACTION).get(Action_.URI)));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(uri) + "%"));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<RoleToActionLink> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::roleToActionLinkToDto).collect(Collectors.toList());

	}

	public RoleToActionLink selectRoleToActionLinkById(long id) {
		RoleToActionLink link = getEntityManager().find(RoleToActionLink.class, id);

		PosValidationException exc = new PosValidationException();
		if (link == null) {
			exc.add(new ValidationHint("roleToActionLinkId", "RoleToActionLink not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return link;
	}

	public RoleToActionLinkDto selectRoleToActionLInkDtoById(long id) {
		RoleToActionLink link = selectRoleToActionLinkById(id);
		return roleToActionLinkToDto(link);
	}

	private RoleToActionLinkDto roleToActionLinkToDto(RoleToActionLink link) {
		RoleToActionLinkDto dto = new RoleToActionLinkDto();
		dto.setActionId(link.getAction().getId());
		dto.setRoleId(link.getRole().getId());
		dto.setId(link.getId());
		dto.setActionType(link.getAction().getType());
		dto.setUserType(link.getRole().getType());
		dto.setUri(link.getAction().getUri());
		return dto;
	}

	public long updateRoleToActionLink(long id, RoleToActionLinkDto dto) {
		RoleToActionLink link = selectRoleToActionLinkById(id);
		link.setAction(actionRepo.selectActionById(dto.getActionId()));
		link.setRole(roleRepo.selectRoleById(dto.getRoleId()));
		return link.getId();
	}

	public void deleteRoleToActionLink(long id) {
		getEntityManager().remove(selectRoleToActionLinkById(id));
	}
}
