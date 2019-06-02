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

import pos.business.domains.UserType;
import pos.dtos.RegisterDto;
import pos.dtos.UserDto;
import pos.util.RestPaths;

// http://localhost:8080/POS-EBA-RSDB/server1/user
@Path(RestPaths.ADMIN)
public interface AdminServices {
	// va returna o lista de utilizatori
	// nu primeste nimic
	// pentru queryParam se apeleaza:
	// http://localhost:8080/POS-EBA-RSDB/server1/user?username=Costel
	// merge si cu nume partial: Cos -> in SQL: LIKE
	// denumirea de dupa semnul intrebarii este cea definita in: exemplu:
	// RestPaths.USERNAME
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	String getUsersRequest(@Context HttpServletRequest httpRequest,
			@QueryParam(RestPaths.USERNAME) String username, @QueryParam(RestPaths.ROLE) UserType role,
			@QueryParam(RestPaths.PASSWORD) String password);

	// va face update la un utilizator
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateUserRequest(@Context HttpServletRequest httpRequest, RegisterDto user);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	String deleteUserRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long userId);

}
