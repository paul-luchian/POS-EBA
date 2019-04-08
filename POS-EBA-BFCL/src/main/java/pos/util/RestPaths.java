package pos.util;

public final class RestPaths {

	// common/general
	public static final String SEP = "/";
	public static final String SERVICES = "services";

	// rest
	public static final String VALIDATION = "validation";
	public static final String LOGIN = "login";
	public static final String REGISTER = "register";

	// paths for tests and rest map
	public static final String PATH_LOGIN = String.join(SEP, SERVICES, VALIDATION, LOGIN);
	public static final String PATH_REGISTER = String.join(SEP, SERVICES, VALIDATION, REGISTER);
}
