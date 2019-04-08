package pos.rest;

import java.io.Serializable;

public class AbstractRestResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rCode = ResponseCodes.OK.code;

	public int getrCode() {
		return rCode;
	}

	public void setrCode(int rCode) {
		this.rCode = rCode;
	}

}
