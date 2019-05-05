package pos.rs.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.business.domains.ActionType;
import pos.business.domains.UserType;
import pos.dtos.RoleToActionLinkDto;
import pos.util.RestPaths;

// http://localhost:8080/POS-EBA-RSDB/server1/role-action?actionType=POST&userType=ADMIN&uri=localhost
@Path(RestPaths.ROLE_ACTION)
public interface RoleToActionLinkServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeRoleToActionLinkRequest(@Context HttpServletRequest httpRequest, RoleToActionLinkDto dto);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<RoleToActionLinkDto> getRoleToActionLinkRequest(@Context HttpServletRequest httpRequest,
			@QueryParam(RestPaths.USER_TYPE) UserType userType,
			@QueryParam(RestPaths.ACTION_TYPE) ActionType actionType, @QueryParam(RestPaths.URI) String uri);

	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	RoleToActionLinkDto getRoleToActionLinkByIdRequest(@Context HttpServletRequest httpRequest,
			@PathParam(RestPaths.ID) long linkId);

	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateRoleToActionLinkRequest(@Context HttpServletRequest httpRequest, RoleToActionLinkDto dto,
			@PathParam(RestPaths.ID) long linkId);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteRoleToActionLinkRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long linkId);

}
