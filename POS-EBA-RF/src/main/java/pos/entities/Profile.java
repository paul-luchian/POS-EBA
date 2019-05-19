package pos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POS_PROFILES")
@NamedQueries({})
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@MapsId
	@OneToOne
	@JoinColumn(name = "ID", nullable = false)
	private User user;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstname;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastname;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
