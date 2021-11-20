package cardsystem.auth;

import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestToken {

    @Test
    public void createDecodeJWT() {

        String accountId = "Nathan Quirke";
        String issuer = "JWT Demo";
        String subject = "Nathan";

        String jwt = JwtEncoder.encodeJWT(
                accountId,
                issuer,
                subject,
                new HashMap<>()
        );
        Claims claims = JwtEncoder.decodeJWT(jwt);
        assertEquals(accountId, claims.getId());
        assertEquals(issuer, claims.getIssuer());
        assertEquals(subject, claims.getSubject());

    }
}