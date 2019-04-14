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

@Entity
@Table(name = "POS_USERS")
@NamedQueries({
		@NamedQuery(name = RfUtil.SELECT_USER_BY_MAIL_PASS, query = "SELECT u FROM User u WHERE u.mail=:mail AND u.password=:password"),
		@NamedQuery(name = RfUtil.SELECT_USER_BY_MAIL, query = "SELECT u FROM User u WHERE u.mail=:mail") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "MAIL", nullable = false, length = 10)
	private String mail;

	@Column(name = "PASSWORD", nullable = false, length = 10)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
