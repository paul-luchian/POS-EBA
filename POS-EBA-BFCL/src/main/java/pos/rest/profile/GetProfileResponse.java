package pos.rest.profile;

import pos.rest.AbstractRestResponse;

public class GetProfileResponse extends AbstractRestResponse {

	private static final long serialVersionUID = 1L;

	private ProfileData profile;

	public ProfileData getProfile() {
		return profile;
	}

	public void setProfile(ProfileData profile) {
		this.profile = profile;
	}

}
