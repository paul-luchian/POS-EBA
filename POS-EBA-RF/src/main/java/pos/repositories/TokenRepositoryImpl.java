package pos.repositories;

import java.io.Serializable;
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
import pos.RfUtil;
import pos.business.domains.ActionType;
import pos.dtos.ActionDto;
import pos.dtos.TokenDto;
import pos.entities.Action;
import pos.entities.Action_;
import pos.entities.Certificate;
import pos.entities.Certificate_;
import pos.entities.Token;
import pos.entities.Token_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "TokenRepository")
@LocalBean
public class TokenRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public long insertToken(TokenDto dto)
	{
		Token token = new Token();
		token.setId(dto.getId());
		token.setUserId(dto.getUserId());
		token.setTokenValue(dto.getTokenValue());
		token.setCreationDate(dto.getCreationDate());
		
		getEntityManager().persist(token);
		
		return token.getId();
		
	}
	
	public List <TokenDto> selectTokens (String tokenValue){
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Token> query = builder.createQuery(Token.class);
		Root<Token> root = query.from(Token.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		if(tokenValue != null) {
			predicates.add(builder.equal(root.get(Token_.TOKEN_VALUE), tokenValue));
		}
		
		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);
		
		
		TypedQuery<Token> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::tokenToDto).collect(Collectors.toList());

	}
	
	private TokenDto tokenToDto(Token token) {
		TokenDto dto = new TokenDto();
		dto.setId(token.getId());
		dto.setUserId(token.getUserId());
		dto.setTokenValue(token.getTokenValue());
		dto.setCreationDate(token.getCreationDate());
		return dto;
	}
	
	public long updateToken(long id, TokenDto dto) {
		Token token = selectTokenById(id);
		token.setId(dto.getId());
		token.setUserId(dto.getUserId());
		token.setTokenValue(dto.getTokenValue());
		token.setCreationDate(dto.getCreationDate());
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

	public void deleteToken(long id) {
		getEntityManager().remove(selectTokenById(id));
	}
}
