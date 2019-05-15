package pos.rs.impl;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pos.dtos.UserDto;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.UserServices;

@Stateless
public class UserServicesImpl implements UserServices {

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepo;
	
	private TokenHandler token_handler  = new TokenHandler();
	@Override
	public String storeUserRequest(HttpServletRequest httpRequest, UserDto user) {
		System.out.println(user);
		long id = userRepo.insertUser(user.getUsername(), user.getPassword());
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public UserDto getUserByIdRequest(HttpServletRequest httpRequest, long userId) {
		UserDto user = userRepo.selectUserDtoById(userId);
		return userRepo.selectUserDtoById(userId);
	}

	@Override
	public String updateUserRequest(HttpServletRequest httpRequest, UserDto user, long userId) {
		System.out.println(user);
		long id = userRepo.updateUser(userId, user);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<UserDto> getUsersRequest(HttpServletRequest httpRequest, String username) {
		return userRepo.selectUsers(username);
	}

	@Override
	public void deleteUserRequest(HttpServletRequest httpRequest, long userId) {
		userRepo.deleteUser(userId);

	}

}
