package pos.rest;

import java.util.HashSet;
import java.util.Set;

public enum ResponseCodes {
	OK(0), ERROR(1), EXCEPTION_THROWN(2), RECORD_NOT_FOUND(3);

	static {
		Set<Integer> rCodes = new HashSet<>();
		for (ResponseCodes rc : values()) {
			if (!rCodes.add(rc.code)) {
				throw new UnsupportedOperationException("Duplicate Response code for: " + rc.code);
			}
		}

	}

	public final int code;

	private ResponseCodes(int code) {
		this.code = code;
	}
}
