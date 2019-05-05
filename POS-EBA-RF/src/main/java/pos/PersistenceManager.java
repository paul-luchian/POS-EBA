package pos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceManager {

	@PersistenceContext(unitName = RfUtil.PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
