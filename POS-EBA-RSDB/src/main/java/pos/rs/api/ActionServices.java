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
import pos.dtos.ActionDto;
import pos.util.RestPaths;

// http://localhost:8080/POS-EBA-RSDB/server1/action?type=POST&uri=local
@Path(RestPaths.ACTION)
public interface ActionServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeActionRequest(@Context HttpServletRequest httpRequest, ActionDto dto);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	List<ActionDto> getActionsRequest(@Context HttpServletRequest httpRequest,
			@QueryParam(RestPaths.ACTION_TYPE) ActionType type, @QueryParam(RestPaths.URI) String uri);

	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	ActionDto getActionRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long actionId);

	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	String updateActionRequest(@Context HttpServletRequest httpRequest, ActionDto dto,
			@PathParam(RestPaths.ID) long actionId);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteActionRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long actionId);

}