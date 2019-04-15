package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.business.domains.UserType;
import pos.entities.Role;
import pos.repositories.RoleRepositoryImpl;
import pos.rs.api.RoleServices;

@Stateless
public class RoleServicesImpl implements RoleServices {

	@EJB(beanName = "RoleRepository")
	private RoleRepositoryImpl roleRepository;
	@Override
	public String storeRoleRequest(HttpServletRequest httpRequest, Role role) {

		
		long id = roleRepository.insertRole(role.getType());
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<Role> getRolesRequest(HttpServletRequest httpRequest) {

		return roleRepository.selectRoles();
	}

	@Override
	public Role getRoleRequest(HttpServletRequest httpRequest, long roleId) {
	
		return roleRepository.selectRoleById(roleId);
	}

	@Override
	public List<Role> getRoleRequest(HttpServletRequest httpRequest, UserType type) {
		// TODO Auto-generated method stub
		return roleRepository.selectRoleByType(type);
	}

	@Override
	public void deleteRoleRequest(HttpServletRequest httpRequest, Role role, long roleId) {
		
		 roleRepository.deleteRoleById(roleId);
	}
	
}
