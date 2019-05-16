package pos.dtos;

import java.io.Serializable;

import pos.business.domains.UserType;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private String password;
	private UserType role;

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

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
