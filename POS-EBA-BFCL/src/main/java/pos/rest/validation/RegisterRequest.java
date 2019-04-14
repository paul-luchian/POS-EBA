package pos.rest.validation;

import java.io.Serializable;

public class RegisterRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String mail;
	private String password;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterRequest: [ password: " + this.password + "; firstName: " + this.firstName + "; lastName: "
				+ this.lastName + "; email:" + this.mail + " ]";
	}
}
