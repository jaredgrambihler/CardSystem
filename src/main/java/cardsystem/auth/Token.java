package cardsystem.auth;

import java.util.Collection;

public interface Token {
    String getUserId();
    Collection<String> getAccountIds();
}
