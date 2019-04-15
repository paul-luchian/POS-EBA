package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.entities.Certificate;
import pos.repositories.CertificateRepositoryImpl;
import pos.rest.certificate.CertificateData;
import pos.rs.api.CertificateServices;

@Stateless
public class CertificateServicesImpl implements CertificateServices {

	@EJB(beanName = "CertificateRepository")
	private CertificateRepositoryImpl certificateRepository;

	@Override
	public String storeCertificateRequest(HttpServletRequest httpRequest, CertificateData certificateData) {
		long id = certificateRepository.insertCertificate(certificateData);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<CertificateData> getCertificatesRequest(HttpServletRequest httpRequest) {
		return certificateRepository.selectCertificates();
	}

	@Override
	public CertificateData getCertificateRequest(HttpServletRequest httpRequest, long certificateId) {
		return certificateRepository.selectCertificateDataById(certificateId);
	}

	@Override
	public String updateCertificateRequest(HttpServletRequest httpRequest, CertificateData certificate,
			long certificateId) {
		System.out.println(certificate);
		long id = certificateRepository.updateCertificate(certificateId, certificate);
		return "{\"id\":\"" + id + "\"}";
	}

}
