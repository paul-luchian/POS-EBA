package pos.repositories;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pos.PersistenceManager;
import pos.entities.User;

@Stateless(name = "UserRepository")
@LocalBean
public class UserRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		em.persist(user);
		return user.getId();
	}

	/*
	 * public List<User> selectUserByMail(String mail) { TypedQuery<User> query =
	 * em.createNamedQuery(RfUtil.SELECT_USER_BY_MAIL, User.class);
	 * query.setParameter("mail", mail); return query.getResultList(); }
	 */
}
