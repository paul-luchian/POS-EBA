package pos.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pos.PersistenceManager;
import pos.dtos.TokenDto;
import pos.entities.Token;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;

@Stateless(name = "TokenRepository")
@LocalBean
public class TokenRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	UserRepositoryImpl userRepo;

	public long insertToken(TokenDto dto) {
		Token token = new Token();
		token.setId(dto.getId());
		token.setUser(userRepo.selectUserById(dto.getUserId()));
		token.setTokenValue(dto.getTokenValue());

		getEntityManager().persist(token);

		return token.getId();

	}

	public List<TokenDto> selectTokensDto(String tokenValue) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Token> query = builder.createQuery(Token.class);
		Root<Token> root = query.from(Token.class);

		List<Predicate> predicates = new ArrayList<>();

		if (tokenValue != null) {
			predicates.add(builder.equal(root.get("tokenValue"), tokenValue));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Token> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::tokenToDto).collect(Collectors.toList());

	}

	public List<Token> selectTokens(String tokenValue) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Token> query = builder.createQuery(Token.class);
		Root<Token> root = query.from(Token.class);

		List<Predicate> predicates = new ArrayList<>();

		if (tokenValue != null) {
			predicates.add(builder.equal(root.get("tokenValue"), tokenValue));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Token> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList();

	}

	public List<Token> selectTokens(long userId) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Token> query = builder.createQuery(Token.class);
		Root<Token> root = query.from(Token.class);

		List<Predicate> predicates = new ArrayList<>();

		if (userId != 0) {
			predicates.add(builder.equal(root.get("user").get("id"), userId));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Token> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList();

	}

	private TokenDto tokenToDto(Token token) {
		TokenDto dto = new TokenDto();
		dto.setId(token.getId());
		dto.setUserId(token.getUser().getId());
		dto.setTokenValue(token.getTokenValue());
		return dto;
	}

	public long updateToken(long id, TokenDto dto) {
		Token token = selectTokenById(id);
		token.setUser(userRepo.selectUserById(dto.getUserId()));
		token.setTokenValue(dto.getTokenValue());
		return token.getId();
	}

	private Token selectTokenById(long id) {
		PosValidationException exc = new PosValidationException();

		Token token = getEntityManager().find(Token.class, id);
		if (token == null) {
			exc.add(new ValidationHint("Id", "Token not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return token;
	}

	public void deleteToken(String value) {
		getEntityManager().remove(selectTokens(value).get(0));
	}
}
