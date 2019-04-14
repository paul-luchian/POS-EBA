package pos.repositories;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.entities.User;

@Stateless(name = "ValidationRepository")
@LocalBean
public class ValidationRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(beanName = "UsersRepository")
	private UsersRepositoryImpl usersRepository;

	public boolean validateToken(String mail, String password) {
		TypedQuery<User> query = em.createNamedQuery(RfUtil.SELECT_USER_BY_MAIL_PASS, User.class);
		query.setParameter("mail", mail).setParameter("password", password);
		return query.getResultList().size() == 1 ? true : false;
	}

}
