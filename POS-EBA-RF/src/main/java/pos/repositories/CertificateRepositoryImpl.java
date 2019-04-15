package pos.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pos.PersistenceManager;
import pos.RfUtil;
import pos.entities.Certificate;
import pos.rest.certificate.CertificateData;

@Stateless(name = "CertificateRepository")
@LocalBean
public class CertificateRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertCertificate(CertificateData certificateData) {
		Date valid_from = new Date(certificateData.getValidFrom() * 1000);
		Date valid_to = new Date(certificateData.getValidTo() * 1000);
		System.out.println(valid_from);
		System.out.println(valid_to);

		Certificate certificate = new Certificate();
		certificate.setPath(certificateData.getPath());
		certificate.setSerialNumber(certificateData.getSerialNumber());
		certificate.setSubject(certificateData.getSubject());
		certificate.setIssuer(certificateData.getIssuer());
		certificate.setSignature(certificateData.getSignature());
		certificate.setValidFrom(valid_from);
		certificate.setValidTo(valid_to);
		em.persist(certificate);
		return certificate.getId();

	}

	public Certificate selectCertificateById(long id) {
		return em.find(Certificate.class, id);
	}

	public CertificateData selectCertificateDataById(long id) {
		// id-ul in find reprezinta primary key-ul din db
		Certificate cert = em.find(Certificate.class, id);
		return certificateToCertificateData(cert);
	}

	public List<CertificateData> selectCertificates() {
		// in RfUtil definim denumirea query-ului din db
		// queryul se gaseste in entitate
		// query-ul se gaseste in User.java
		TypedQuery<Certificate> query = em.createNamedQuery(RfUtil.SELECT_CERTIFICATES, Certificate.class);

		return query.getResultList().stream().map(data -> certificateToCertificateData(data))
				.collect(Collectors.toList());
	}

	public CertificateData certificateToCertificateData(Certificate cert) {
		CertificateData certData = new CertificateData();
		certData.setId(cert.getId());
		certData.setIssuer(cert.getIssuer());
		certData.setPath(cert.getPath());
		certData.setSerialNumber(cert.getSerialNumber());
		certData.setSignature(cert.getSignature());
		certData.setSubject(cert.getSubject());
		certData.setValidFrom(cert.getValidFrom().getTime());
		certData.setValidTo(cert.getValidTo().getTime());
		return certData;
	}

	public long updateCertificate(long id, CertificateData certificate) {
		Certificate certificateFromDb = selectCertificateById(id);
		certificateFromDb.setPath(certificate.getPath());
		certificateFromDb.setSerialNumber(certificate.getSerialNumber());
		certificateFromDb.setSubject(certificate.getSubject());
		certificateFromDb.setIssuer(certificate.getIssuer());
		certificateFromDb.setSignature(certificate.getSignature());
		certificateFromDb.setValidFrom(new Date(certificate.getValidFrom()));
		certificateFromDb.setValidTo(new Date(certificate.getValidTo()));
		return id;
	}
}
