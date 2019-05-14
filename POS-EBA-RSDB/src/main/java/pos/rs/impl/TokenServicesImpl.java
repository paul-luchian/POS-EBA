package pos.rs.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import pos.dtos.TokenDto;
import pos.dtos.UserDto;
import pos.repositories.TokenRepositoryImpl;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.TokenServices;

@Stateless
public class TokenServicesImpl implements TokenServices {

	@EJB(beanName = "TokenRepository")
	private TokenRepositoryImpl tokenRepo;

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepo;

	@Override
	public String storeTokenRequest(HttpServletRequest httpRequest, TokenDto dto) {
		long id = tokenRepo.insertToken(dto);
		return "{\"id\":\"" + id + "\"}";
	}

	private TokenHandler tk = new TokenHandler();

	@Override
	public Response auth(HttpServletRequest httpRequest, long userId) {

		UserDto user = userRepo.selectUserDtoById(userId);
		String token = tk.generateToken(user);
		System.out.println("Token = "+token);
		System.out.println("User = "+user.getUsername());
		
		return Response.ok().header("Authorization", "Bearer " + token).build();
		 
//		Response resp = Response.ok("User" + " authenticated").header("jwt", tk.generateToken(user)).build();
//		System.out.println("Response=" + resp);
//		return resp;
	}
}
