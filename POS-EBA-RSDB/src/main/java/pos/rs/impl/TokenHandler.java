package pos.rs.impl;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pos.dtos.UserDto;

public class TokenHandler {
	private int expirationTime =  7 * 24 * 60 * 60 * 1000;;

	//Sample method to construct a JWT
	public String generateToken(UserDto user) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("testKey");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
		System.out.println("Key="+signingKey.toString());
		System.out.println("Username="+user.getUsername());
		System.out.println("Password="+user.getPassword());
		final Map<String, Object> tokenData = new HashMap<>();
		tokenData.put("username", user.getUsername());
		tokenData.put("password", user.getPassword());
		tokenData.put("create_date", LocalDateTime.now());
		final JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(tokenData);
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, expirationTime );
		jwtBuilder.setExpiration(calendar.getTime());
		String token  = jwtBuilder.signWith(SignatureAlgorithm.HS512, signingKey).compact();
		decryptToken(token);
		return token;
	}

	public void decryptToken(String jwtToken)
	{
		java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
		String[] parts = jwtToken.split("\\."); // split out the "parts" (header, payload and signature)

		String headerJson = new String(decoder.decode(parts[0]));
		String payloadJson = new String(decoder.decode(parts[1]));
		String signatureJson = new String(decoder.decode(parts[2]));

		System.out.println("header"+headerJson);
		System.out.println("payload"+payloadJson);
		System.out.println("signature"+signatureJson);      
	}


}
