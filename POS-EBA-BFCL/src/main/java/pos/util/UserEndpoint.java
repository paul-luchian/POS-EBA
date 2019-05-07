//package pos.util;
//
//
//
//import java.security.Key;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//import javax.crypto.KeyGenerator;
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//
//import org.jboss.security.auth.spi.Users.User;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Path("/")
//
//public class UserEndpoint {
//
//
//	private static final long EXPIRATION_LIMIT = 30;
//	private KeyGenerator keyGenerator;
//	private Key key = keyGenerator.generateKey();
//
////	@Inject
//	//
//
//	//@POST
//	//@Path("/login")
//	// @Consumes(APPLICATION_FORM_URLENCODED)
//	//    public Response authenticateUser(@FormParam("login") String login,
//	//                                     @FormParam("password") String password) {
//	public Response authenticateUser(User user) {
//		try {
//			// Authenticate the user using the credentials provided
//			boolean ok = VerifyCredentials(user.getName(),user.getPassword());
//			if(ok) {
//				// Issue a token for the user
//				String token = issueToken(user.getName());
//
//				// Return the token on the response
//				return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
//			}
//			else
//			{
//				return Response.status(Response.Status.UNAUTHORIZED).build();
//			}
//
//		} catch (Exception e) {
//			return Response.status(Response.Status.UNAUTHORIZED).build();
//		}
//	}
//
//	private boolean VerifyCredentials(String name, String password) {
//		return true;
//	}
//	private String issueToken(String login) {
//
//
//		String jwtToken = Jwts.builder()
//				.setSubject(login)
//				// .setIssuer(uriInfo.getAbsolutePath().toString())
//				.setIssuedAt(new Date())
//				.setExpiration(getExpirationDate())
//				.signWith(SignatureAlgorithm.HS512, key)
//				.compact();
//		return jwtToken;
//	}
//	private Date getExpirationDate() {
//		long currentTimeInMillis = System.currentTimeMillis();
//
//		long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
//		return new Date(currentTimeInMillis + expMilliSeconds);
//	}
//}
