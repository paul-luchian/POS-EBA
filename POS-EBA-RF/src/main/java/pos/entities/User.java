package pos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pos.RfUtil;

// @NamedQuery(name = RfUtil.SELECT_USER_BY_MAIL_PASS, query = "SELECT u FROM User u WHERE u.mail=:mail AND u.password=:password"),
@Entity
@Table(name = "POS_USERS")
@NamedQueries({})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "USERNAME", nullable = false, length = 10, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false, length = 10)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
