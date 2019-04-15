package pos;

public final class RfUtil {

	public static final String MYSQL_UNIT = "server1";

	public static final String PERSISTENCE_UNIT = MYSQL_UNIT;

	// USERS
	public static final String SELECT_ALL_USERS = "selectUsers";
	public static final String SELECT_USER_BY_ID = "selectUserById";

	// CERTIFICATES
	public static final String SELECT_CERTIFICATE_BY_ID = "selectCertificateById";
	public static final String SELECT_CERTIFICATES = "selectCertificates";

	// ACTIONS
	public static final String SELECT_ALL_ACTIONS = "selectAllActions";
	public static final String SELECT_ACTIONS_BY_URI = "selectActionsByUri";
	public static final String SELECT_ACTIONS_BY_TYPE = "selectActionsByType";
	public static final String SELECT_ACTION_BY_ID = "selectActionById";
	public static final String SELECT_ACTIONS_BY_URI_AND_TYPE = "selectActionsByUriAndType";

	// ROLES
	public static final String SELECT_ALL_ROLES = "selectAllRoles";
	public static final String SELECT_ROLES_BY_ID = "selectRolesById";
	public static final String SELECT_ROLES_BY_TYPE = "selectRolesByType";

}
