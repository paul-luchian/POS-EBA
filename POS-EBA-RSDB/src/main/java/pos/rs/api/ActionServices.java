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
import pos.entities.Action;
import pos.util.RestPaths;

@Path(RestPaths.ACTION)
public interface ActionServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String actionRequest(@Context HttpServletRequest httpRequest, Action action);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	List<Action> getActionsRequest(@Context HttpServletRequest httpRequest);

	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	Action getActionRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long actionId);

	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	List<Action> getActionRequest(@Context HttpServletRequest httpRequest, @QueryParam(RestPaths.TYPE) ActionType type,
			@QueryParam(RestPaths.TYPE) String uri);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteActionRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long actionId);

}