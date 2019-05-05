package pos.rs.api;

import javax.ws.rs.Path;

import pos.util.RestPaths;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.business.domains.UserType;
import pos.dtos.RoleDto;
// http://localhost:8080/POS-EBA-RSDB/server1/role?type=ADMIN
@Path(RestPaths.ROLE)
public interface RoleServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeRoleRequest(@Context HttpServletRequest httpRequest, RoleDto dto);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<RoleDto> getRolesRequest(@Context HttpServletRequest httpRequest,
			@QueryParam(RestPaths.USER_TYPE) UserType userType);

	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	RoleDto getRoleRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long roleId);

	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateRoleRequest(@Context HttpServletRequest httpRequest, RoleDto dto,
			@PathParam(RestPaths.ID) long roleId);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteRoleRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long roleId);

}
