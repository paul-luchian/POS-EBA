package pos.util;

public final class RestPaths {
	public static final String APPLICATION_PATH = "http://localhost:8080/POS-EBA-PF";
	// common/general
	public static final String SEP = "/";
	public static final String SERVICES = "services";
	public static final String LIST = "list";

	// rest
	public static final String VALIDATION = "validation";
	public static final String LOGIN = "login";
	public static final String REGISTER = "register";

	public static final String PROFILES = "profiles";

	// paths for tests and rest map
	public static final String PATH_LOGIN = String.join(SEP, SERVICES, VALIDATION, LOGIN);
	public static final String PATH_REGISTER = String.join(SEP, SERVICES, VALIDATION, REGISTER);
	public static final String PROFILES_LIST = String.join(SEP, SERVICES, PROFILES, LIST);
	public static final String PROFILE = String.join(SEP, SERVICES, PROFILES);
	public static final String PATH_APPLICATION_COOKIE = String.join(SEP, APPLICATION_PATH);
}
