package pos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({})
@Table(name = "POS_TOKENS")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue
//	@Column(name = "ID", nullable = false)
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "USER_ID", nullable = false)
	private long userId;

	@Column(name = "TOKEN_VALUE", nullable = false)
	private String tokenValue;

	@Column(name = "CREATED_AT", nullable = false)
//	@Temporal(TemporalType.DATE)
	private long creationDate;
	

	
}

