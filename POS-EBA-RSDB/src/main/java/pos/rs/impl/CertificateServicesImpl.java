package pos.rs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import pos.dtos.CertificateDto;
import pos.repositories.CertificateRepositoryImpl;
import pos.rs.api.CertificateServices;

@Stateless
public class CertificateServicesImpl implements CertificateServices {

	@EJB(beanName = "CertificateRepository")
	private CertificateRepositoryImpl certificateRepo;

	@Override
	public String storeCertificateRequest(HttpServletRequest httpRequest, CertificateDto dto) {
		long id = certificateRepo.insertCertificate(dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public List<CertificateDto> getCertificatesRequest(HttpServletRequest httpRequest, String issuer,
			String serialNumber, String subject) {
		return certificateRepo.selectCertificates(serialNumber, issuer, subject);
	}

	@Override
	public CertificateDto getCertificateRequest(HttpServletRequest httpRequest, long certificateId) {
		return certificateRepo.selectCertificateDtoById(certificateId);
	}

	@Override
	public String updateCertificateRequest(HttpServletRequest httpRequest, CertificateDto dto, long certificateId) {
		long id = certificateRepo.updateCertificate(certificateId, dto);
		return "{\"id\":\"" + id + "\"}";
	}

	@Override
	public void deleteCertificateRequest(HttpServletRequest httpRequest, long certificateId) {
		certificateRepo.deleteCertificate(certificateId);

	}

}
