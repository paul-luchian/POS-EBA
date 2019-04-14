package pos.repositories;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.entities.User;

@Stateless(name = "UsersRepository")
@LocalBean
public class UsersRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertUser(String mail, String password) {
		User user = new User();
		user.setMail(mail);
		user.setPassword(password);
		em.persist(user);
		return user.getId();
	}

	public long deleteUser(String mail) {
		List<User> list = selectUserByMail(mail);
		User user = !list.isEmpty() ? list.get(0) : null;
		em.remove(user);
		return user.getId();
	}

	public List<User> selectUserByMail(String mail) {
		TypedQuery<User> query = em.createNamedQuery(RfUtil.SELECT_USER_BY_MAIL, User.class);
		query.setParameter("mail", mail);
		return query.getResultList();
	}
}
