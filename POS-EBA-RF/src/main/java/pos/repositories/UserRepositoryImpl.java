package pos.repositories;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.entities.User;
import pos.util.BCrypt;

@Stateless(name = "UserRepository")
@LocalBean
public class UserRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
		user.setPassword(hashed);
		// for debug only
		//System.out.println("Hashed password"+user.getPassword());
		//System.out.println( "check" + BCrypt.checkpw("qqqq", user.getPassword()));
		
		em.persist(user);
		
		return user.getId();
	}

	public User selectUserById(long id) {
		// id-ul in find reprezinta primary key-ul din db
		return em.find(User.class, id);
	}

	public List<User> selectUsers() {
		// in RfUtil definim denumirea query-ului din db
		// queryul se gaseste in entitate
		// query-ul se gaseste in User.java
		TypedQuery<User> query = em.createNamedQuery(RfUtil.SELECT_ALL_USERS, User.class);
		return query.getResultList();
	}

	public long updateUser(long id, User user) {
		User userFromDb = selectUserById(id);
		userFromDb.setPassword(user.getPassword());
		userFromDb.setUserName(user.getUserName());
		return id;
	}
}
