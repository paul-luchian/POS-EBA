package pos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pos.RfUtil;
import pos.business.domains.UserType;

@Entity
@Table(name = "POS_ROLES")
@NamedQueries({ @NamedQuery(name = RfUtil.SELECT_ALL_ROLES, query = "SELECT r FROM Role r"),
	@NamedQuery(name = RfUtil.SELECT_ROLES_BY_ID, query = "SELECT r FROM Role r WHERE r.id=:id"),
	@NamedQuery(name = RfUtil.SELECT_ROLES_BY_TYPE, query = "SELECT r FROM Role r WHERE r.type=:type"),
 })



public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", nullable = false)
	private UserType type;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	

}
