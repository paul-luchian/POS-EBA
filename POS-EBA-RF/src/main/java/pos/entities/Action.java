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
import pos.business.domains.ActionType;

@Entity
@Table(name = "POS_ACTIONS")
@NamedQueries({ @NamedQuery(name = RfUtil.SELECT_ALL_ACTIONS, query = "SELECT a FROM Action a"),
		@NamedQuery(name = RfUtil.SELECT_ACTION_BY_ID, query = "SELECT a FROM Action a WHERE a.id=:id"),
		@NamedQuery(name = RfUtil.SELECT_ACTIONS_BY_TYPE, query = "SELECT a FROM Action a WHERE a.type=:type"),
		@NamedQuery(name = RfUtil.SELECT_ACTIONS_BY_URI, query = "SELECT a FROM Action a WHERE a.uri=:uri"),
		@NamedQuery(name = RfUtil.SELECT_ACTIONS_BY_URI_AND_TYPE, query = "SELECT a FROM Action a WHERE a.uri=:uri AND a.type=:type"), })
public class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", nullable = false)
	private ActionType type;

	@Column(name = "URI", nullable = false)
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