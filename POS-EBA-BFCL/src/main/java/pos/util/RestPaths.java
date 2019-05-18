package pos.util;

public final class RestPaths {
	public static final String APPLICATION_PATH = "http://localhost:8080/POS-EBA-PF";
	public static final String DATABASE1_PATH = "http://localhost:8080/POS-EBA-RSDB";

	public static final String DATABASE1_NAME = "server1";

	// common/general
	public static final String SEP = "/";
	public static final String ID_SERVICE = "/{id}";
	public static final String ID = "id";
	public static final String SERVICES = "services";
	public static final String LIST = "list";
	public static final String TYPE = "type";
	public static final String URI = "uri";

	// query param
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String ISSUER = "issuer";
	public static final String SERIAL_NUMBER = "serialNumber";
	public static final String SUBJECT = "subject";
	public static final String ACTION_TYPE = "type";
	public static final String ACTION_URI = "uri";
	public static final String USER_TYPE = "userType";

	// tables base path
	public static final String USER = "user";
	public static final String ROLE = "role";
	public static final String PROFILE = "profile";
	public static final String ACTION = "action";
	public static final String CERTIFICATE = "certificates";
	public static final String TOKENS = "tokens";
	public static final String ROLE_ACTION = "role-action";

	// rest
	public static final String REGISTER = "register";
	public static final String LOGIN = "login";
	// paths for tests and rest map
	public static final String PATH_APPLICATION_COOKIE = String.join(SEP, APPLICATION_PATH);
}