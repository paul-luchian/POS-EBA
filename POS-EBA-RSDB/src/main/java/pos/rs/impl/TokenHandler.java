package pos.rs.impl;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.client.HttpClient;
import org.jboss.security.identity.RoleType;
import org.json.JSONObject;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pos.business.domains.UserType;
import pos.dtos.UserDto;

public class TokenHandler {
	private static int expirationTime = 30 * 60 * 1000;

	// Sample method to construct a JWT
	public static String generateToken(String username, UserType role, long userid) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("testKey");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

		System.out.println("Key=" + signingKey.toString());
		System.out.println("Username=" + username);
		System.out.println("Role=" + role);

		final Map<String, Object> tokenData = new HashMap<>();
		tokenData.put("username", username);
		tokenData.put("role", role);
		tokenData.put("create_date", System.currentTimeMillis());
		tokenData.put("userid", userid);

		final JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(tokenData);

		long currentTimeInMillis = System.currentTimeMillis();
		Date d = new Date(currentTimeInMillis + expirationTime);

		jwtBuilder.setExpiration(d);

		String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, signingKey).compact();
		return token;
	}

	public static String[] decryptToken(String jwtToken) {
		Decoder decoder = Base64.getUrlDecoder();
		String[] parts = jwtToken.split("\\."); // split out the "parts" (header, payload and signature)

		String headerJson = new String(decoder.decode(parts[0]));
		String payloadJson = new String(decoder.decode(parts[1]));
		String signatureJson = new String(decoder.decode(parts[2]));

		System.out.println("header" + headerJson);
		System.out.println("payload" + payloadJson);
		System.out.println("signature" + signatureJson);
		String ret[] = { headerJson, payloadJson, signatureJson };
		return ret;
	}

}
