package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import pos.business.BusinessContext;
import pos.dtos.TokenDto;
import pos.dtos.UserDto;
import pos.repositories.TokenRepositoryImpl;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.TokenServices;
import pos.rs.utils.TokenContext;
import pos.util.BCrypt;

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
	public Response auth(HttpServletRequest httpRequest, UserDto dto) {

		TokenContext tCtxt = TokenContext.from(httpRequest);

		if (TokenContext.isSetted(tCtxt)) {

			// token setat, de verificat info
			System.out.println("TOKEN SETAT");
			List<TokenDto> list = tokenRepo.selectTokensDto(tCtxt.getToken());
			if (list.size() != 1) {
				return Response.status(400).build();
			} else {
				// trebuie verificat token

				List<UserDto> listToken = userRepo.selectUsers(tCtxt.getUsername(), tCtxt.getRole(), null);
				if (list.size() != 1) {
					return Response.status(400).build();
				} else {
					UserDto user = listToken.get(0);
					if (user != null) {
						// user existent, se
						if (tCtxt.getExpirationDate().getTime() < System.currentTimeMillis()) {
							return Response.status(200).header(HttpHeaders.AUTHORIZATION, "Bearer " + tCtxt.getToken())
									.build();

						} else {

							String token = TokenHandler.generateToken(user.getUsername(), user.getRole(), user.getId());

							TokenDto temp = new TokenDto();
							temp.setTokenValue(token);
							temp.setUserId(user.getId());
							tokenRepo.updateToken(list.get(0).getId(), temp);

							System.out.println("Token = " + token);
							System.out.println("User = " + user.getUsername());
							return Response.status(200).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
						}

					} else {
						// user inexistent
						// eroare
						return Response.status(400).build();
					}

				}
			}

		} else {

			// token nu e setat. Creez token
			System.out.println("TOKEN NESETAT");
			List<UserDto> list = userRepo.selectUsers(dto.getUsername(), null, dto.getPassword());
			if (list.size() != 1) {
				return Response.status(400).build();
			} else {
				UserDto user = list.get(0);

				if (user != null && BCrypt.checkpw(dto.getPassword(), user.getPassword())) {
					// user existent si cu credentiale valide
					String token = TokenHandler.generateToken(user.getUsername(), user.getRole(), user.getId());
					TokenDto temp = new TokenDto();
					temp.setTokenValue(token);
					temp.setUserId(user.getId());
					tokenRepo.insertToken(temp);
					System.out.println("Token = " + token);
					System.out.println("User = " + user.getUsername());

					return Response.status(200).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

				} else {
					// user inexistent sau credentiale invalide
					return Response.status(400).build();
				}

			}
		}

	}
}
