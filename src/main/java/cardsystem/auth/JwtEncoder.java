package cardsystem.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtEncoder {

    public static String encodeJWT(String accountId, String issuer, String subject, Map<String, Object> headers) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(getPrivateKeyBytes(), signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setId(accountId)
                .setSubject(subject)
                .setIssuer(issuer)
                .setHeader(headers)
                .setExpiration(getExpirationDate())
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPrivateKeyBytes())
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
    
    private static byte[] getPrivateKeyBytes() {
        // this isn't secure, but leave it here for ease of implementation
        String privateKey = "4C8kum4LxyKWYLM78sKdXrzbBjDCFyfX";
        return DatatypeConverter.parseBase64Binary(privateKey);
    }

    private static Date getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 12);
        return calendar.getTime();
    }

}
