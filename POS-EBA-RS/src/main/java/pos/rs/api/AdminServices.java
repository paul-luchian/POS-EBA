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

	// va returna un singur utilizator
	// primeste id-ul userului de returnat
	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	UserDto getUserByIdRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long userId);

	// va face update la un utilizator
	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateUserRequest(@Context HttpServletRequest httpRequest, UserDto user,
			@PathParam(RestPaths.ID) long userId);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteUserRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long userId);

}
