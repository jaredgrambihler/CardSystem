package cardsystem.auth;

import cardsystem.user.User;
import cardsystem.user.UserFetcher;
import io.jsonwebtoken.Claims;

import java.util.*;

public class TokenFactory {

    /**
     * Get a login token.
     * 
     * @param email    users email
     * @param password users password
     * @return a token if the login is valid, an empty optional if it is invalid
     */
    public static Optional<Token> getLoginToken(String email, String password) {
        if (!isValidPassword(email, password)) {
            return Optional.empty();
        }
        Optional<User> userOptional = UserFetcher.loadUserFromEmail(email);
        if (!userOptional.isPresent()) {
            return Optional.empty();
        }
        User user = userOptional.get();
        String userId = user.getUserId();
        Collection<String> accountIds = user.getAccountIds();
        Map<String, Object> tokenHeaders = new HashMap<>();
        tokenHeaders.put("userId", userId);
        tokenHeaders.put("accountIds", accountIds);
        String encodedToken = JwtEncoder.encodeJWT(user.getUserId(), "Card System", "Subject", tokenHeaders);
        return Optional.of(new JwtToken(userId, accountIds, encodedToken));
    }

    /**
     * Create a token.
     * 
     * @param authToken auth token
     * @return token instance
     */
    public static Optional<Token> createToken(String authToken) {
        Claims claims = JwtEncoder.decodeJWT(authToken);
        String userId = (String) claims.getOrDefault("userId", null);
        Collection<String> accountIds = (Collection<String>) claims.getOrDefault("accountIds", null);
        if (userId == null || accountIds == null || !isValidToken(claims)) {
            return Optional.empty();
        }
        return Optional.of(new JwtToken(userId, accountIds, authToken));
    }

    private static boolean isValidPassword(String email, String password) {
        // for sake of simplicity of the project, we'll always say a password is valid
        return true;
    }

    private static boolean isValidToken(Claims claims) {
        return claims.getExpiration().after(new Date()) &&
                claims.getIssuer().equals("Card System");
    }

}
