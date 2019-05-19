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
import pos.business.domains.UserType;
import pos.dtos.RoleToUserLinkDto;
// import pos.entities.Action_;
import pos.entities.Role;
// import pos.entities.RoleToActionLink_;
import pos.entities.RoleToUserLink;
// import pos.entities.RoleToUserLink_;
// import pos.entities.Role_;
import pos.entities.User;
// import pos.entities.User_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "RoleToUserLinkRepository")
@LocalBean
public class RoleToUserLinkRepositoryImpl extends PersistenceManager {
	@EJB
	UserRepositoryImpl userRepo;

	@EJB
	RoleRepositoryImpl roleRepo;

	public long insertRoleToUserLink(RoleToUserLinkDto dto) {
		PosValidationException exc = new PosValidationException();

		User user = userRepo.selectUserById(dto.getUserId());
		if (user == null) {
			exc.add(new ValidationHint("userId", "User not found!"));
		}
		Role role = roleRepo.selectRoleById(dto.getRoleId());
		if (role == null) {
			exc.add(new ValidationHint("roleId", "Role not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		RoleToUserLink link = new RoleToUserLink();
		link.setRole(role);
		link.setUser(user);
		getEntityManager().persist(link);
		return link.getId();
	}

	public List<RoleToUserLinkDto> selectRoleToActionLinks(UserType userType, String username) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<RoleToUserLink> query = builder.createQuery(RoleToUserLink.class);
		Root<RoleToUserLink> root = query.from(RoleToUserLink.class);

		List<Predicate> predicates = new ArrayList<>();
		if (userType != null) {
			// predicates.add(builder.equal(root.get(RoleToUserLink_.ROLE).get(Role_.TYPE),
			// userType));
			predicates.add(builder.equal(root.get("role").get("type"), userType));
		}
		if (!StringUtility.isBlank(username)) {
			// Expression<String> exp = builder.trim('
			// ',builder.lower(root.get(RoleToUserLink_.USER).get(User_.USERNAME)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("user").get("username")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(username) + "%"));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<RoleToUserLink> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::roleToUserLinkToDto).collect(Collectors.toList());

	}

	public RoleToUserLink selectRoleToUserLinkById(long id) {
		PosValidationException exc = new PosValidationException();
		RoleToUserLink link = getEntityManager().find(RoleToUserLink.class, id);
		if (link == null) {
			exc.add(new ValidationHint("roleToUserLinkId", "RoleToUserLink not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return link;
	}

	public RoleToUserLinkDto selectRoleToUserLinkDtoById(long id) {
		RoleToUserLink link = selectRoleToUserLinkById(id);
		return roleToUserLinkToDto(link);
	}

	public RoleToUserLinkDto roleToUserLinkToDto(RoleToUserLink link) {
		RoleToUserLinkDto dto = new RoleToUserLinkDto();
		dto.setId(link.getId());
		dto.setRoleId(link.getRole().getId());
		dto.setUserId(link.getUser().getId());
		dto.setUsername(link.getUser().getUsername());
		dto.setUserType(link.getRole().getType());
		return dto;
	}

	public long updateRoleToUserLink(long id, RoleToUserLinkDto dto) {
		RoleToUserLink link = selectRoleToUserLinkById(id);
		link.setUser(userRepo.selectUserById(dto.getUserId()));
		link.setRole(roleRepo.selectRoleById(dto.getRoleId()));
		return link.getId();
	}

	void deleteRoleToUserLink(long id) {
		getEntityManager().remove(selectRoleToUserLinkById(id));
	}
}
