package pos;

public final class RfUtil {

	public static final String MYSQL_UNIT = "server1";

	public static final String PERSISTENCE_UNIT = MYSQL_UNIT;

	// QUERIES
	public static final String SELECT_USER_BY_ID = "selectUserById";
	public static final String SELECT_ALL_USERS = "selectUsers";

	// ACTIONS
	public static final String SELECT_ALL_ACTIONS = "selectAllActions";
	public static final String SELECT_ACTIONS_BY_URI = "selectActionsByUri";
	public static final String SELECT_ACTIONS_BY_TYPE = "selectActionsByType";
	public static final String SELECT_ACTION_BY_ID = "selectActionById";
	public static final String SELECT_ACTIONS_BY_URI_AND_TYPE = "selectActionsByUriAndType";
}