package pos.repositories;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pos.PersistenceManager;

@Stateless(name = "ProfilesRepository")
@LocalBean
public class ProfilesRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

}
