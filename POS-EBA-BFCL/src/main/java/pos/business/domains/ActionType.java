package pos.business.domains;

public enum ActionType {
	GET(0), POST(1), PUT(2), DELETE(3);

	public static final String DOMAIN_NAME = "DOMAIN_POS_ACTION_TYPES";

	public final int code;

	private ActionType(int code) {
		this.code = code;
	}
}
