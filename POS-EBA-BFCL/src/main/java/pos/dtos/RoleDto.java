package pos.dtos;

import java.io.Serializable;

import pos.business.domains.UserType;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
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
