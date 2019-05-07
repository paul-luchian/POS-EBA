package pos.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.dtos.TokenDto;
import pos.util.RestPaths;

@Path(RestPaths.TOKENS)
public interface TokenServices {

	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeTokenRequest(@Context HttpServletRequest httpRequest, TokenDto dto);

}
