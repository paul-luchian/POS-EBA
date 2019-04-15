package pos.repositories;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.business.domains.UserType;
import pos.entities.Role;

@Stateless (name ="RoleRepository")
@LocalBean
public class RoleRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertRole(UserType type) {
		Role r = new Role();
		r.setType(type);
		em.persist(r);
		return r.getId();
	}

	public List<Role> selectRoles() {

		TypedQuery<Role> query = em.createNamedQuery(RfUtil.SELECT_ALL_ROLES, Role.class);
		return query.getResultList();
	}

	public Role selectRoleById(long id) {
		return em.find(Role.class, id);
	}
	
	public List<Role> selectRoleByType(UserType type) {
		TypedQuery<Role> query = em.createNamedQuery(RfUtil.SELECT_ROLES_BY_TYPE, Role.class);
		query.setParameter("type", type);
		return query.getResultList();
	}

	public void deleteRoleById(long id) {
		em.remove(selectRoleById(id));
	}
}
