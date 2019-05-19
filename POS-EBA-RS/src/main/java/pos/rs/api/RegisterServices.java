package pos.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.dtos.RegisterDto;
import pos.util.RestPaths;

@Path(RestPaths.REGISTER)
public interface RegisterServices {
	// va insera un utilizator
	// returneaza id-ul inregistrarii inserate
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String registerUserRequest(@Context HttpServletRequest httpRequest, RegisterDto user);

}
