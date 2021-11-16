package cardsystem.email;

public interface Email {
    boolean userIsOptedIn();
    String getToAddress();
    String getFromAddress();
    String getSubject();
    String getHeader();
    String getBody();
    String getFooter();
}
