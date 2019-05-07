package pos.dtos;

import java.io.Serializable;
import java.util.Date;



public class TokenDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private long userId;
	private String tokenValue;
	private long creationDate;

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

	
	



}
