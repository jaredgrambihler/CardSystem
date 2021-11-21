package cardsystem.auth;

import java.util.Collection;

public class JwtToken implements Token {

    private String userId;
    private Collection<String> accountIds;
    private String encodedToken;

    public JwtToken(String userId, Collection<String> accountIds, String encodedToken) {
        this.userId = userId;
        this.accountIds = accountIds;
        this.encodedToken = encodedToken;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public Collection<String> getAccountIds() {
        return accountIds;
    }

    @Override
    public String encode() {
        return encodedToken;
    }
}
