package pos.dtos;

import java.io.Serializable;

import pos.business.domains.ActionType;
import pos.business.domains.UserType;

public class RoleToActionLinkDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long actionId;
	private long roleId;
	private UserType userType;
	private ActionType actionType;
	private String uri;

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
