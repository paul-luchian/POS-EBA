package pos.rest.validation;

import java.io.Serializable;

public class RegisterRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String mail;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "RegisterRequest: [ userName: " + this.userName + "; password: " + this.password + "; firstName: "
				+ this.firstName + "; lastName: " + this.lastName + "; mail:" + this.mail + " ]";
	}
}
