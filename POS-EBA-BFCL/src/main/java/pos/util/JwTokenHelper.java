//package pos.util;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//import javax.crypto.KeyGenerator;
//
//
//import io.jsonwebtoken.*;
//
//
//
//public class JwTokenHelper {
//
//
//	
//   private static JwTokenHelper jwTokenHelper = null;
//   private static final long EXPIRATION_LIMIT = 30;
//
//   private KeyGenerator keyGenerator;
//   private Key key = keyGenerator.generateKey();
//
//   private JwTokenHelper() {
//   }
//
//   public static JwTokenHelper getInstance() {
//      if(jwTokenHelper == null)
//           jwTokenHelper = new JwTokenHelper();
//      return jwTokenHelper;
//   }
//
//   //  1
//   //Add the username  set expiration date for privateKey and sign in with SignatureAlgorithm.HS512.
//   public String generatePrivateKey(String username) {
//       return Jwts
//                .builder()
//                .setSubject(username)
//                .setExpiration(getExpirationDate())
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
//   }
//   
//   // 2 Validating the key with Jwts parser.
//   public void claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException  {
//      Jwts
//                .parser()
//                .setSigningKey(key)
//                .parseClaimsJws(privateKey);
//   }
//
//   // 3 sets that the privateKey is only valid for the next thirty minutes.
//   private Date getExpirationDate() {
//       long currentTimeInMillis = System.currentTimeMillis();
//       long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
//       return new Date(currentTimeInMillis + expMilliSeconds);
//   }
//
//}