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
import pos.entities.Action;
// import pos.entities.Action_;
import pos.entities.Certificate;
// import pos.entities.Certificate_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.StringUtility;

@Stateless(name = "ActionRepository")
@LocalBean
public class ActionRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertAction(ActionDto dto) {
		Action action = new Action();
		action.setType(dto.getType());
		action.setUri(dto.getUri());
		getEntityManager().persist(action);
		return action.getId();
	}

	public List<ActionDto> selectActions(ActionType type, String uri) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Action> query = builder.createQuery(Action.class);
		Root<Action> root = query.from(Action.class);

		List<Predicate> predicates = new ArrayList<>();
		if (type != null) {
			// predicates.add(builder.equal(root.get(Action_.TYPE), type));
			predicates.add(builder.equal(root.get("type"), type));
		}
		if (!StringUtility.isBlank(uri)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Action_.URI)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("uri")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(uri) + "%"));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Action> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::actionToDto).collect(Collectors.toList());
	}

	public Action selectActionById(long id) {
		PosValidationException exc = new PosValidationException();

		Action action = getEntityManager().find(Action.class, id);
		if (action == null) {
			exc.add(new ValidationHint("actionId", "Action not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return action;
	}

	public ActionDto selectActionDtoById(long id) {
		Action action = selectActionById(id);
		return actionToDto(action);
	}

	private ActionDto actionToDto(Action action) {
		ActionDto dto = new ActionDto();
		dto.setId(action.getId());
		dto.setType(action.getType());
		dto.setUri(action.getUri());
		return dto;
	}

	public long updateAction(long id, ActionDto dto) {
		Action action = selectActionById(id);
		action.setType(dto.getType());
		action.setUri(dto.getUri());
		return action.getId();
	}

	public void deleteAction(long id) {
		getEntityManager().remove(selectActionById(id));
	}
}