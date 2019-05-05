package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.business.domains.UserType;
import pos.dtos.RoleDto;
import pos.repositories.RoleRepositoryImpl;
import pos.rs.api.RoleServices;

@Stateless
public class RoleServicesImpl implements RoleServices {

	@EJB(beanName = "RoleRepository")
	private RoleRepositoryImpl roleRepo;

	@Override
	public String storeRoleRequest(HttpServletRequest httpRequest, RoleDto dto) {
		long id = roleRepo.insertRole(dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<RoleDto> getRolesRequest(HttpServletRequest httpRequest, UserType userType) {
		return roleRepo.selectRoles(userType);
	}

	@Override
	public RoleDto getRoleRequest(HttpServletRequest httpRequest, long roleId) {
		return roleRepo.selectRoleDtoById(roleId);
	}

	@Override
	public String updateRoleRequest(HttpServletRequest httpRequest, RoleDto dto, long roleId) {
		long id = roleRepo.updateRole(roleId, dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public void deleteRoleRequest(HttpServletRequest httpRequest, long roleId) {
		roleRepo.deleteRole(roleId);

	}

}
