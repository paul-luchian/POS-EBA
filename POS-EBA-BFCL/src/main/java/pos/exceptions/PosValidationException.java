package pos.exceptions;

import java.util.ArrayList;
import java.util.List;

public class PosValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final List<ValidationHint> hints = new ArrayList<>();

	public void add(ValidationHint hint) {
		hints.add(hint);
	}

	public List<ValidationHint> getHints() {
		return hints;
	}

}
