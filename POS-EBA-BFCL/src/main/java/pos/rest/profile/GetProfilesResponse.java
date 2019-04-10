package pos.rest.profile;

import java.util.List;

import pos.rest.AbstractRestResponse;

public class GetProfilesResponse extends AbstractRestResponse {

	private static final long serialVersionUID = 1L;

	private List<ProfileData> profiles;

	public List<ProfileData> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileData> profiles) {
		this.profiles = profiles;
	}

}
