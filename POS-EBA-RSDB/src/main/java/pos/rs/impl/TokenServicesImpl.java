package pos.rs.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.dtos.TokenDto;
import pos.repositories.TokenRepositoryImpl;
import pos.rs.api.TokenServices;
@Stateless
public class TokenServicesImpl implements TokenServices{
	
	@EJB(beanName = "TokenRepository")
	private TokenRepositoryImpl tokenRepo;
	@Override
	public String storeTokenRequest(HttpServletRequest httpRequest, TokenDto dto) {
		long id = tokenRepo.insertToken(dto);
		return "{\"id\":\"" + id + "\"}";
	}
}
