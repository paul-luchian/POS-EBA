package pos.rs.api;

import javax.ws.rs.Path;

import pos.util.RestPaths;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pos.entities.Certificate;
import pos.rest.certificate.CertificateData;

@Path(RestPaths.CERTIFICATE)
public interface CertificateServices {

	// va insera un certificat
	// returneaza id-ul inregistrarii inserate
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String storeCertificateRequest(@Context HttpServletRequest httpRequest, CertificateData certificateData);

	// va returna o lista de certificate
	// nu primeste nimic
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<CertificateData> getCertificatesRequest(@Context HttpServletRequest httpRequest);

	// va returna un singur certificat
	// primeste id-ul certificatului de returnat
	@GET
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	CertificateData getCertificateRequest(@Context HttpServletRequest httpRequest,
			@PathParam(RestPaths.ID) long certificateId);

	// va face update la un certificat
	@POST
	@Path(RestPaths.ID_SERVICE)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String updateCertificateRequest(@Context HttpServletRequest httpRequest, CertificateData certificate,
			@PathParam(RestPaths.ID) long certificateId);

}
