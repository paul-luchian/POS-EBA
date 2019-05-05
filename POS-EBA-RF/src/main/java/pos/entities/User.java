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
@NamedQueries({ @NamedQuery(name = RfUtil.SELECT_USER_BY_ID, query = "SELECT u FROM User u WHERE u.id=:id"),
		@NamedQuery(name = RfUtil.SELECT_ALL_USERS, query = "SELECT u FROM User u"), })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "USERNAME", nullable = false, length = 10, unique = true)
	private String userName;

	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
