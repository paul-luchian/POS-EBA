package pos.rs.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pos.dtos.TokenDto;
import pos.dtos.UserDto;
import pos.repositories.TokenRepositoryImpl;
import pos.repositories.UserRepositoryImpl;
import pos.rs.utils.TokenContext;

@Stateless(name = "CheckToken")
@LocalBean
public class CheckTokenLogic {

	@EJB(beanName = "TokenRepository")
	private TokenRepositoryImpl tokenRepo;

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepo;

	public boolean check(TokenContext tCtxt) {
		boolean flag = false;
		if (TokenContext.isSetted(tCtxt)) {
			List<TokenDto> list = tokenRepo.selectTokensDto(tCtxt.getToken());
			if (list.size() != 1) {
				// token-ul nu a fost gasit
				flag = false;
			} else {
				// token gasit in db -> se verifica datele
				List<UserDto> listToken = userRepo.selectUsers(tCtxt.getUsername(), tCtxt.getRole(), null);
				if (list.size() != 1) {
					// user din token not found
					flag = false;
				} else {
					// user din token gasit in db
					UserDto user = listToken.get(0);
					if (user != null) {
						if (tCtxt.getExpirationDate().getTime() < System.currentTimeMillis()) {
							// token valid
							flag = true;

						} else {
							// token expirat -> relogare
							flag = false;
						}
					} else {
						// user not found
						flag = false;
					}

				}
			}

		}
		return flag;
	}

}
