package pos.repositories;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.entities.Profile;
import pos.entities.User;

@Stateless(name = "ProfilesRepository")
@LocalBean
public class ProfilesRepositoryImpl extends PersistenceManager implements Serializable {

	@EJB(beanName = "UsersRepository")
	private UsersRepositoryImpl usersRepository;

	private static final long serialVersionUID = 1L;

	public void insertProfile(String firstName, String lastName, String mail) {
		Profile profile = new Profile();
		profile.setFirstName(firstName);
		profile.setLastName(lastName);
		List<User> list = usersRepository.selectUserByMail(mail);
		User user = !list.isEmpty() ? list.get(0) : null;
		profile.setUser(user);
		em.persist(profile);
	}

	public void deleteProfile(String mail) {
		List<User> list = usersRepository.selectUserByMail(mail);
		User user = !list.isEmpty() ? list.get(0) : null;
		em.remove(selectProfileByUser(user));
	}

	public Profile selectProfileByUser(User user) {
		TypedQuery<Profile> query = em.createNamedQuery(RfUtil.SELECT_PROFILE_BY_USER_ID, Profile.class);
		query.setParameter("userId", user.getId());
		return query.getSingleResult();
	}
}
