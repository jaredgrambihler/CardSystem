package cardsystem.auth;

import java.util.Base64;
import java.util.Optional;
import io.jsonwebtoken.*;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import jakarta.xml.bind.DatatypeConverter;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenFactory {

    /**
     * Get a login token.
     * 
     * @param email    users email
     * @param password users password
     * @return a token if the login is valid, an empty optional if it is invalid
     */

    public static Optional<Token> getLoginToken(String email, String password) {
        return Optional.empty();
    }

    /**
     * Create a token.
     * 
     * @param authToken auth token
     * @return token instance
     */

    public static Token createToken(String authToken) {
        return null;
    }

    public static final String secretKey = "4C8kum4LxyKWYLM78sKdXrzbBjDCFyfX";


    public static String encodeJWT(String accountId, String issuer, String subject) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        JwtBuilder builder = Jwts.builder().setId(accountId).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    
    public static Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
