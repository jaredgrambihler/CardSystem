package cardsystem.Auth;

import java.util.Collection;

public interface Token {
    String getUserId();
    Collection<String> getAccountIds();
    String encode();
}
