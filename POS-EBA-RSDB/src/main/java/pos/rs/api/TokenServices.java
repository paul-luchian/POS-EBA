package pos.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pos.dtos.TokenDto;
import pos.dtos.UserDto;
import pos.util.RestPaths;

@Path(RestPaths.TOKENS)
public interface TokenServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeTokenRequest(@Context HttpServletRequest httpRequest, TokenDto dto);

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	Response auth(@Context HttpServletRequest httpRequest, UserDto user);

}
