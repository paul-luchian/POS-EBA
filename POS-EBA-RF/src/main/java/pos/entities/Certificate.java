package pos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "POS_CERTIFICATES")
public class Certificate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "PATH", nullable = false)
	private String path;

	@Column(name = "SERIAL_NUMBER", nullable = false)
	private String serialNumber;

	@Column(name = "SUBJECT", nullable = false)
	private String subject;

	@Column(name = "ISSUER", nullable = false)
	private String issuer;

	@Column(name = "SIGNATURE", nullable = false)
	private String signature;

	@Column(name = "VALID_FROM", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date validFrom;

	@Column(name = "VALID_TO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date validTo;

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

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}
