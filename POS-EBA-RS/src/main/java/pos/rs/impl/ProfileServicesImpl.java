package pos.rs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pos.business.BusinessContext;
import pos.rest.profile.GetProfileResponse;
import pos.rest.profile.GetProfilesResponse;
import pos.rest.profile.ProfileData;
import pos.rs.api.ProfileServices;

public class ProfileServicesImpl implements ProfileServices {

	@Override
	public GetProfileResponse getProfileRequest(HttpServletRequest httpRequest, long id) {
		BusinessContext bsContext = BusinessContext.from(httpRequest);
		GetProfileResponse response = new GetProfileResponse();
		ProfileData profile = new ProfileData();
		profile.setEmail("test@email.com  " + id);
		profile.setFirstName("First Name  " + id);
		profile.setLastName("Last Name  " + id);

		response.setProfile(profile);
		return response;
	}

	@Override
	public GetProfilesResponse getProfilesRequest(HttpServletRequest httpRequest) {
		BusinessContext bsContext = BusinessContext.from(httpRequest);
		GetProfilesResponse response = new GetProfilesResponse();

		List<ProfileData> profiles = new ArrayList<>();

		ProfileData profile = new ProfileData();
		profile.setEmail("test@email.com  1");
		profile.setFirstName("First Name  1");
		profile.setLastName("Last Name  1");
		profiles.add(profile);

		profile = new ProfileData();
		profile.setEmail("test@email.com  2");
		profile.setFirstName("First Name  2");
		profile.setLastName("Last Name  2");
		profiles.add(profile);
		response.setProfiles(profiles);
		return response;
	}

}
