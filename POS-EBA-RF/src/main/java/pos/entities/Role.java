package pos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pos.business.domains.UserType;

@Entity
@Table(name = "POS_ROLES")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", nullable = false)
	private UserType type;

}
