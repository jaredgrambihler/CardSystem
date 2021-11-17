package cardsystem.auth;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestToken {

    @Test
    public void createDecodeJWT() {

        String accountId = "Nathan Quirke";
        String issuer = "JWT Demo";
        String subject = "Nathan";

        String jwt = TokenFactory.encodeJWT(
                accountId,
                issuer,
                subject
        );
        Claims claims = TokenFactory.decodeJWT(jwt);
        assertEquals(accountId, claims.getId());
        assertEquals(issuer, claims.getIssuer());
        assertEquals(subject, claims.getSubject());

    }
}