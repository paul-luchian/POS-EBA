package pos.repositories;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.business.domains.ActionType;
import pos.entities.Action;

@Stateless(name = "ActionRepository")
@LocalBean
public class ActionRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertAction(ActionType type, String uri) {
		Action action = new Action();
		action.setType(type);
		action.setUri(uri);
		em.persist(action);
		return action.getId();
	}

	public List<Action> selectActions() {
		TypedQuery<Action> query = em.createNamedQuery(RfUtil.SELECT_ALL_ACTIONS, Action.class);
		return query.getResultList();
	}

	public Action selectActionById(long id) {
		return em.find(Action.class, id);
	}

	public List<Action> selectActionsByUri(String uri) {
		TypedQuery<Action> query = em.createNamedQuery(RfUtil.SELECT_ACTIONS_BY_URI, Action.class);
		query.setParameter("uri", uri);
		return query.getResultList();
	}

	public List<Action> selectActionsByType(ActionType type) {
		TypedQuery<Action> query = em.createNamedQuery(RfUtil.SELECT_ACTIONS_BY_TYPE, Action.class);
		query.setParameter("type", type);
		return query.getResultList();
	}

	public List<Action> selectActionsByUriAndType(String uri, ActionType type) {
		TypedQuery<Action> query = em.createNamedQuery(RfUtil.SELECT_ACTIONS_BY_URI_AND_TYPE, Action.class);
		query.setParameter("type", type);
		query.setParameter("uri", uri);
		return query.getResultList();
	}

	public void deleteActionById(long id) {
		em.remove(selectActionById(id));
	}
}