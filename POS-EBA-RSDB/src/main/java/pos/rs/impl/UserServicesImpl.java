package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.entities.User;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.UserServices;

@Stateless
public class UserServicesImpl implements UserServices {
	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepository;

	@Override
	public String storeUserRequest(HttpServletRequest httpRequest, User user) {
		System.out.println(user);
		long id = userRepository.insertUser(user.getUserName(), user.getPassword());
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<User> getUsersRequest(HttpServletRequest httpRequest) {
		return userRepository.selectUsers();
	}

	@Override
	public User getUserRequest(HttpServletRequest httpRequest, long userId) {
		return userRepository.selectUserById(userId);
	}

	@Override
	public String updateUserRequest(HttpServletRequest httpRequest, User user, long userId) {
		System.out.println(user);
		long id = userRepository.updateUser(userId, user);
		return "{\"id\":\"" + id + "\"}";
	}

}
