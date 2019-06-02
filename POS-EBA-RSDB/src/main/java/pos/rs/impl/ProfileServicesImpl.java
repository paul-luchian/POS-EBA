package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.dtos.ProfileDto;
import pos.repositories.ProfileRepositoryImpl;
import pos.rs.api.ProfileServices;

@Stateless
public class ProfileServicesImpl implements ProfileServices {

	@EJB(beanName = "ProfileRepository")
	private ProfileRepositoryImpl profileRepo;

	@Override
	public String storeProfileRequest(HttpServletRequest httpRequest, ProfileDto dto) {
		long id = profileRepo.insertProfile(dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<ProfileDto> getProfilesRequest(HttpServletRequest httpRequest, String username, String firstName,
			String lastName, String email) {
		return profileRepo.selectProfiles(username, firstName, lastName, email);
	}

	@Override
	public ProfileDto getProfileByIdRequest(HttpServletRequest httpRequest, long id) {
		return profileRepo.selectProfileDtoById(id);
	}

	@Override
	public String updateProfileRequest(HttpServletRequest httpRequest, ProfileDto dto, long profileId) {
		long id = profileRepo.updateProfile(profileId, dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public void deleteProfileRequest(HttpServletRequest httpRequest, long profileId) {
		profileRepo.deleteProfile(profileId);

	}

}
