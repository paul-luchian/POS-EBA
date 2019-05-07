//package pos.util;
//import java.io.IOException;
//import java.security.Key;
//
//import javax.annotation.Priority;
//import javax.crypto.KeyGenerator;
//import javax.inject.Inject;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//
//import io.jsonwebtoken.Jwts;
//
//@Provider
//
//
//public class JWTTokenNeededFilter implements ContainerRequestFilter {
//    @Inject
//    private KeyGenerator keyGenerator;
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//    	
//    	
//    	
//        // Get the HTTP Authorization header from the request
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        // Extract the token from the HTTP Authorization header
//        String token = authorizationHeader.substring("Bearer".length()).trim();
//        try {
//            // Validate the token
//            Key key = keyGenerator.generateKey();
//            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
//            System.out.println("#### valid token : " + token);
//        } catch (Exception e) {
//        	 System.out.println("#### invalid token : " + token);
//        	 
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        }
//    }
//}