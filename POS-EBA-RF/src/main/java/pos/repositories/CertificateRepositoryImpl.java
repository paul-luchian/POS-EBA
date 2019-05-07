package pos.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pos.PersistenceManager;
import pos.dtos.CertificateDto;
import pos.entities.Certificate;
// import pos.entities.Certificate_;
import pos.exceptions.PosValidationException;
import pos.exceptions.ValidationHint;
import pos.util.DateUtility;
import pos.util.StringUtility;

@Stateless(name = "CertificateRepository")
@LocalBean
public class CertificateRepositoryImpl extends PersistenceManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public long insertCertificate(CertificateDto dto) {
		Date valid_from = DateUtility.longToDate(dto.getValidFrom());
		Date valid_to = DateUtility.longToDate(dto.getValidTo());

		Certificate certificate = new Certificate();
		certificate.setPath(dto.getPath());
		certificate.setSerialNumber(dto.getSerialNumber());
		certificate.setSubject(dto.getSubject());
		certificate.setIssuer(dto.getIssuer());
		certificate.setSignature(dto.getSignature());
		certificate.setValidFrom(valid_from);
		certificate.setValidTo(valid_to);
		getEntityManager().persist(certificate);
		return certificate.getId();

	}

	public CertificateDto selectCertificateDtoById(long id) {
		PosValidationException exc = new PosValidationException();

		Certificate certificate = getEntityManager().find(Certificate.class, id);
		if (certificate == null) {
			exc.add(new ValidationHint("certificateId", "Certificate not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return certificateToDto(certificate);

	}

	public Certificate selectCertificateById(long id) {
		PosValidationException exc = new PosValidationException();

		Certificate certificate = getEntityManager().find(Certificate.class, id);
		if (certificate == null) {
			exc.add(new ValidationHint("certificateId", "Certificate not found!"));
		}
		if (!exc.getHints().isEmpty()) {
			throw exc;
		}
		return certificate;
	}

	public List<CertificateDto> selectCertificates(String serialNumber, String issuer, String subject) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Certificate> query = builder.createQuery(Certificate.class);
		Root<Certificate> root = query.from(Certificate.class);

		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtility.isBlank(serialNumber)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Certificate_.SERIAL_NUMBER)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("serialNumber")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(serialNumber) + "%"));
		}
		if (!StringUtility.isBlank(issuer)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Certificate_.ISSUER)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("issuer")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(issuer) + "%"));
		}
		if (!StringUtility.isBlank(subject)) {
			// Expression<String> exp = builder.trim(' ',
			// builder.lower(root.get(Certificate_.SUBJECT)));
			Expression<String> exp = builder.trim(' ', builder.lower(root.get("subject")));
			predicates.add(builder.like(exp, "%" + StringUtility.cleanString(subject) + "%"));
		}

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicatesArray);

		TypedQuery<Certificate> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList().stream().map(this::certificateToDto).collect(Collectors.toList());
	}

	private CertificateDto certificateToDto(Certificate certificate) {
		CertificateDto dto = new CertificateDto();
		dto.setId(certificate.getId());
		dto.setIssuer(certificate.getIssuer());
		dto.setPath(certificate.getPath());
		dto.setSerialNumber(certificate.getSerialNumber());
		dto.setSignature(certificate.getSignature());
		dto.setSubject(certificate.getSubject());
		dto.setValidFrom(DateUtility.dateToLong(certificate.getValidFrom()));
		dto.setValidTo(DateUtility.dateToLong(certificate.getValidTo()));
		return dto;
	}

	public long updateCertificate(long id, CertificateDto dto) {
		Certificate certificateFromDb = selectCertificateById(id);
		certificateFromDb.setPath(dto.getPath());
		certificateFromDb.setSerialNumber(dto.getSerialNumber());
		certificateFromDb.setSubject(dto.getSubject());
		certificateFromDb.setIssuer(dto.getIssuer());
		certificateFromDb.setSignature(dto.getSignature());
		certificateFromDb.setValidFrom(DateUtility.longToDate(dto.getValidFrom()));
		certificateFromDb.setValidTo(DateUtility.longToDate(dto.getValidTo()));
		return certificateFromDb.getId();
	}

	public void deleteCertificate(long id) {
		getEntityManager().remove(selectCertificateById(id));
	}
}
