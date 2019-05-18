package pos.rs.api;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.dtos.UserDto;
import pos.util.RestPaths;

@Path(RestPaths.LOGIN)
public interface LoginServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	void loginRequest( @Context HttpServletRequest httpRequest, UserDto user);

}