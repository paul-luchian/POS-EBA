package pos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceManager {

	@PersistenceContext(unitName = RfUtil.PERSISTENCE_UNIT)
	protected EntityManager em;
}
