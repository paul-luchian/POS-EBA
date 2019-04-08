package pos.business.domains;

public enum UserType {
	ADMIN("Admin"), GUEST("Guest"), USER("User");

	public static final String DOMAIN_NAME = "DOMAIN_POS_USER_TYPES";

	public final String description;

	private UserType(String description) {
		this.description = description;
	}

}
