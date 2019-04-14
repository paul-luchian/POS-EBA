package pos.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pos.rest.user.PutUserRequest;
import pos.util.RestPaths;

@Path(RestPaths.USER)
public interface UserServices {
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response putUserRequest(@Context HttpServletRequest httpRequest, PutUserRequest user);
}
