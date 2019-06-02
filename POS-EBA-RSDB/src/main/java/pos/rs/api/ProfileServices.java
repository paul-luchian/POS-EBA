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

import pos.dtos.ProfileDto;
import pos.util.RestPaths;

// http://localhost:8080/POS-EBA-RSDB/server1/profile
@Path(RestPaths.PROFILE)
public interface ProfileServices {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeProfileRequest(@Context HttpServletRequest httpRequest, ProfileDto dto);

	// exemple
	// http://localhost:8080/POS-EBA-RSDB/server1/profile?firstname=a&lastname=c
	// http://localhost:8080/POS-EBA-RSDB/server1/profile?firstname=a
	// http://localhost:8080/POS-EBA-RSDB/server1/profile?firstname=a&lastname=c&username=e
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<ProfileDto> getProfilesRequest(@Context HttpServletRequest httpRequest,
			@QueryParam(RestPaths.USERNAME) String username, @QueryParam(RestPaths.FIRSTNAME) String firstName,
			@QueryParam(RestPaths.LASTNAME) String lastName, @QueryParam(RestPaths.EMAIL) String email);

	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	ProfileDto getProfileByIdRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long profileId);

	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateProfileRequest(@Context HttpServletRequest httpRequest, ProfileDto dto,
			@PathParam(RestPaths.ID) long profileId);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteProfileRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long profileId);

}
