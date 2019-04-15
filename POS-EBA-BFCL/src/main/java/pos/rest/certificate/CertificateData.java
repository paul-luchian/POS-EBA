package pos.rest.certificate;

import java.io.Serializable;
import java.util.Date;


public class CertificateData implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String path;
	private String serialNumber;
	private String subject;
	private String issuer;
	private String signature;

	private long validFrom;
	private long validTo;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(long validFrom) {
		this.validFrom = validFrom;
	}
	public long getValidTo() {
		return validTo;
	}
	public void setValidTo(long validTo) {
		this.validTo = validTo;
	}
	


}
