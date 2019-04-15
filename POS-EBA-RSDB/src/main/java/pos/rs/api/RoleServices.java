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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.business.domains.UserType;
import pos.entities.Role;




@Path(RestPaths.ROLE)
public interface RoleServices {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeRoleRequest(@Context HttpServletRequest httpRequest, Role role);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Role> getRolesRequest(@Context HttpServletRequest httpRequest);
	
	
	
	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	Role getRoleRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long roleId);

	
	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	List<Role> getRoleRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.TYPE) UserType type);

	


	
	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteRoleRequest(@Context HttpServletRequest httpRequest,Role role, @PathParam(RestPaths.ID) long roleId);


	
	
}
