package pos.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pos.PersistenceManager;
import pos.dtos.UserDto;
import pos.entities.User;
import pos.entities.User_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "UserRepository")
@LocalBean
public class UserRepositoryImpl extends PersistenceManager {

	public long insertUser(String userName, String password) {
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		// inseareaza o noua inregistrare in tabela (persist)
		// dupa persist informatiile sunt cele din baza de date
		getEntityManager().persist(user);
		// id-ul este cel obtinut dupa inserare
		return user.getId();
	}

	// pentru un id dat intoarce userul
	public User selectUserById(long id) {
		// in metoda ,,find,, , id-ul reprezinta primary key-ul din db
		User user = getEntityManager().find(User.class, id);

		PosValidationException exc = new PosValidationException();
		if (user == null) {
			exc.add(new ValidationHint("userId", "Profile not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}

		return user;
	}

	public UserDto selectUserDtoById(long id) {
		User user = selectUserById(id);
		return userToDto(user);
	}

	private UserDto userToDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setPassword(user.getPassword());
		dto.setUsername(user.getUsername());
		return dto;
	}

	// intoarce doar userName-urile
	public List<String> selectUsernames() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<User> root = query.from(User.class);

		query.select(root.get(User_.USERNAME));

		TypedQuery<String> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList();
	}

	public List<UserDto> selectUsers(String username) {
		PosValidationException exc = new PosValidationException();

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);

		// aici adaugam toate conditiile noastre
		List<Predicate> predicates = new ArrayList<>();

		if (username != null) {
			Expression<String> exp = builder.trim(' ', builder.lower(root.get(User_.USERNAME)));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(username) + "%"));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<User> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::userToDto).collect(Collectors.toList());
	}

	public long updateUser(long id, UserDto user) {
		// aici adaugam toate exceptiile pe care le gasim
		PosValidationException exc = new PosValidationException();
		User userFromDb = selectUserById(id);
		// pentru a face update la niste informatii trebuie sa avem un utilizator
		// existent
		if (userFromDb == null) {
			exc.add(new ValidationHint("userId", "User not found!"));
		}
		// daca avem exceptii adaugate, le aruncam -> throw
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		userFromDb.setPassword(user.getPassword());
		userFromDb.setUsername(user.getUsername());
		return id;
	}

	public void deleteUser(long id) {
		getEntityManager().remove(selectUserById(id));
	}
}
