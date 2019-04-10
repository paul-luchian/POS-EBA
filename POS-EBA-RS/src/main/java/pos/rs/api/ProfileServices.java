package pos.rs.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.rest.profile.GetProfileResponse;
import pos.rest.profile.GetProfilesResponse;
import pos.util.RestPaths;

@Path(RestPaths.PROFILES)
public interface ProfileServices {
	// /services/profiles/3 -> list profile 3
	// return type {rCode,profile:{firstName,lastName,email}}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	GetProfileResponse getProfileRequest(@Context HttpServletRequest httpRequest, @PathParam("id") long id);

	// /services/profiles/list -> list all profiles
	// return type
	// {rCode,profiles:[{firstName,lastName,email},{firstName,lastName,email}, ...]}
	@GET
	@Path(RestPaths.LIST)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	GetProfilesResponse getProfilesRequest(@Context HttpServletRequest httpRequest);

}
