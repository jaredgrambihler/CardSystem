package cardsystem.auth;

import java.util.Optional;

public class TokenFactory {

    /**
     * Get a login token.
     * @param email users email
     * @param password users password
     * @return a token if the login is valid, an empty optional if it is invalid
     */
    public static Optional<Token> getLoginToken(String email, String password) {
        return Optional.empty();
    }

    /**
     * Create a token.
     * @param authToken auth token
     * @return token instance
     */
    public static Token createToken(String authToken) {
        return null;
    }
}
