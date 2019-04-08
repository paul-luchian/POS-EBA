package pos.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.rest.validation.LoginRequest;
import pos.rest.validation.LoginResponse;
import pos.rest.validation.RegisterRequest;
import pos.rest.validation.RegisterResponse;
import pos.util.RestPaths;

@Path(RestPaths.VALIDATION)
public interface ValidationServices {

	@POST
	@Path(RestPaths.LOGIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	LoginResponse loginRequest(@Context HttpServletRequest httpRequest, LoginRequest request);

	@POST
	@Path(RestPaths.REGISTER)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	RegisterResponse loginRequest(@Context HttpServletRequest httpRequest, RegisterRequest request);
}
