package pos.rs.api;

import javax.ws.rs.Path;

import pos.util.RestPaths;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.dtos.CertificateDto;
import pos.entities.Certificate;

// http://localhost:8080/POS-EBA-RSDB/server1/certificates?issuer=bbb&serialNumber=b&subject=c
@Path(RestPaths.CERTIFICATE)
public interface CertificateServices {

	// va insera un certificat
	// returneaza id-ul inregistrarii inserate
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeCertificateRequest(@Context HttpServletRequest httpRequest, CertificateDto dto);

	// va returna o lista de certificate
	// nu primeste nimic
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<CertificateDto> getCertificatesRequest(@Context HttpServletRequest httpRequest,
			@QueryParam(RestPaths.ISSUER) String issuer, @QueryParam(RestPaths.SERIAL_NUMBER) String serialNumber,
			@QueryParam(RestPaths.SUBJECT) String subject);

	// va returna un singur certificat
	// primeste id-ul certificatului de returnat
	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	CertificateDto getCertificateRequest(@Context HttpServletRequest httpRequest,
			@PathParam(RestPaths.ID) long certificateId);

	// va face update la un certificat
	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateCertificateRequest(@Context HttpServletRequest httpRequest, CertificateDto dto,
			@PathParam(RestPaths.ID) long certificateId);

	@DELETE
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	void deleteCertificateRequest(@Context HttpServletRequest httpRequest, @PathParam(RestPaths.ID) long certificateId);

}
