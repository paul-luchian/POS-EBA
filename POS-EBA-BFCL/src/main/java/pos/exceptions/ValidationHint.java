package pos.exceptions;

import java.io.Serializable;

public class ValidationHint implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;
	private String message;

	public ValidationHint() {
		super();
	}

	public ValidationHint(String label, String message) {
		super();
		this.label = label;
		this.message = message;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
