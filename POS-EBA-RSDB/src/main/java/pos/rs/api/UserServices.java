package pos.rs.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.entities.User;
import pos.util.RestPaths;

@Path(RestPaths.USER)
public interface UserServices {
	// va insera un utilizator
	// returneaza id-ul inregistrarii inserate
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeUserRequest(@Context HttpServletRequest httpRequest, User user);

	// va returna o lista de utilizatori
	// nu primeste nimic
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<User> getUsersRequest(@Context HttpServletRequest httpRequest);

	// va returna un singur utilizator
	// primeste id-ul userului de returnat
	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	User getUserRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long userId);

	// va face update la un utilizator
	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateUserRequest(@Context HttpServletRequest httpRequest, User user, @PathParam(RestPaths.ID) long userId);

}
