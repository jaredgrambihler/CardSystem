package cardsystem.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.junit.Test;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.commons.logging.*;
import static org.junit.Assert.*;
import java.util.logging.LogManager;

public class TestToken {

    private static final Log4JLogger logger = LogManager.logger();


    @Test
    public void createDecodeJWT() {

        String accountId = "Nathan Quirke";
        String issuer = "JWT Demo";
        String subject = "Nathan";

        String jwt = TokenFactory.encodeJWT(accountId, issuer, subject)
                jwtAccountId, 
                jwtIssuer, 
                jwtSubject, 
        );
        
        logger.info("jwt = \"" + jwt.toString() + "\"");

        Claims claims = TokenFactory.decodeJWT(jwt);

        logger.info("claims = " + claims.toString());

        assertEquals(jwtAccountId, claims.getId());
        assertEquals(jwtIssuer, claims.getIssuer());
        assertEquals(jwtSubject, claims.getSubject());

    }
}