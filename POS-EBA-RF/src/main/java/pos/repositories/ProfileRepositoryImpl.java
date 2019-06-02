package pos.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pos.PersistenceManager;
import pos.dtos.ProfileDto;
import pos.entities.Profile;
// import pos.entities.Profile_;
import pos.entities.User;
// import pos.entities.User_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "ProfileRepository")
@LocalBean
public class ProfileRepositoryImpl extends PersistenceManager implements Serializable {

	@EJB
	UserRepositoryImpl userRepo;

	private static final long serialVersionUID = 1L;

	public long insertProfile(ProfileDto dto) {
		PosValidationException exc = new PosValidationException();
		User user = userRepo.selectUserById(dto.getId());
		if (user == null) {
			exc.add(new ValidationHint("userId", "User not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		Profile profile = new Profile();
		profile.setUser(user);
		profile.setLastname(dto.getLastname());
		profile.setFirstname(dto.getFirstname());
		profile.setEmail(dto.getEmail());
		getEntityManager().persist(profile);
		return profile.getId();
	}

	public List<ProfileDto> selectProfiles(String username, String firstName, String lastName, String email) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Profile> query = builder.createQuery(Profile.class);
		Root<Profile> root = query.from(Profile.class);

		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtility.isBlank(username)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Profile_.USER).get(User_.USERNAME)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("user").get("username")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(username) + "%"));
		}
		if (!StringUtility.isBlank(firstName)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Profile_.FIRSTNAME)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("firstname")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(firstName) + "%"));
		}
		if (!StringUtility.isBlank(lastName)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Profile_.LASTNAME)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("lastname")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(lastName) + "%"));
		}
		if (!StringUtility.isBlank(email)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Profile_.LASTNAME)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("email")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(email) + "%"));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Profile> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::profileToProfileDto).collect(Collectors.toList());
	}

	public ProfileDto selectProfileDtoById(long id) {
		Profile profile = getEntityManager().find(Profile.class, id);

		PosValidationException exc = new PosValidationException();
		if (profile == null) {
			exc.add(new ValidationHint("profileId", "Profile not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return profileToProfileDto(profile);
	}

	private ProfileDto profileToProfileDto(Profile profile) {
		ProfileDto dto = new ProfileDto();
		dto.setFirstname(profile.getFirstname());
		dto.setLastname(profile.getLastname());
		dto.setId(profile.getUser().getId());
		dto.setUsername(profile.getUser().getUsername());
		dto.setEmail(profile.getEmail());
		return dto;
	}

	public long updateProfile(long profileId, ProfileDto dto) {
		Profile profile = getEntityManager().find(Profile.class, profileId);

		PosValidationException exc = new PosValidationException();
		if (profile == null) {
			exc.add(new ValidationHint("profile ID", "Profile not found!"));
		}
		User user = userRepo.selectUserById(dto.getId());
		if (user == null) {
			exc.add(new ValidationHint("user ID", "User not found!"));
		}

		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		profile.setFirstname(dto.getFirstname());
		profile.setLastname(dto.getLastname());

		return profile.getId();
	}

	public Profile selectProfileById(long id) {
		Profile profile = getEntityManager().find(Profile.class, id);

		PosValidationException exc = new PosValidationException();
		if (profile == null) {
			exc.add(new ValidationHint("profile ID", "Profile not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return profile;
	}

	public void deleteProfile(long id) {
		getEntityManager().remove(selectProfileById(id));
	}
}
