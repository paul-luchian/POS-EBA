package pos.dtos;

import java.io.Serializable;

import pos.business.domains.ActionType;

public class ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private ActionType type;
	private String uri;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
