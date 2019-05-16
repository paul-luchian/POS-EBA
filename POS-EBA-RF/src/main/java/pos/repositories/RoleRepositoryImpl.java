package pos.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.business.domains.UserType;
import pos.dtos.RoleDto;
import pos.entities.Profile;
// import pos.entities.Profile_;
import pos.entities.Role;
// import pos.entities.Role_;
// import pos.entities.User_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "RoleRepository")
@LocalBean
public class RoleRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertRole(RoleDto dto) {
		Role role = new Role();
		role.setType(dto.getType());
		getEntityManager().persist(role);
		return role.getId();
	}

	public List<RoleDto> selectRoles(UserType userType) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);

		List<Predicate> predicates = new ArrayList<>();
		if (userType != null) {
			// predicates.add(builder.equal(root.get(Role_.TYPE), userType));
			predicates.add(builder.equal(root.get("type"), userType));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Role> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::roleToDto).collect(Collectors.toList());
	}

	public Role selectRoleById(long id) {
		PosValidationException exc = new PosValidationException();

		Role role = getEntityManager().find(Role.class, id);
		if (role == null) {
			exc.add(new ValidationHint("roleId", "Role not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return role;
	}

	public Role selectRole(UserType userType) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Role> query = builder.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);

		List<Predicate> predicates = new ArrayList<>();
		if (userType != null) {
			// predicates.add(builder.equal(root.get(Role_.TYPE), userType));
			predicates.add(builder.equal(root.get("type"), userType));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Role> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getSingleResult();
	}

	public RoleDto selectRoleDtoById(long id) {
		Role role = selectRoleById(id);
		return roleToDto(role);
	}

	private RoleDto roleToDto(Role role) {
		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setType(role.getType());
		return dto;
	}

	public long updateRole(long id, RoleDto dto) {
		Role role = selectRoleById(id);
		role.setType(dto.getType());
		return role.getId();
	}

	public void deleteRole(long id) {
		getEntityManager().remove(selectRoleById(id));
	}
}
