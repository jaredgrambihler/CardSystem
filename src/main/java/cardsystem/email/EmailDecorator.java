package cardsystem.email;

public class EmailDecorator implements Email {

    private Email email;

    public EmailDecorator(Email email) {
        this.email = email;
    }

    @Override
    public boolean userIsOptedIn() {
        return email.userIsOptedIn();
    }

    @Override
    public String getToAddress() {
        return email.getToAddress();
    }

    @Override
    public String getFromAddress() {
        return email.getFromAddress();
    }

    @Override
    public String getSubject() {
        return email.getSubject();
    }

    @Override
    public String getHeader() {
        return email.getHeader();
    }

    @Override
    public String getBody() {
        return email.getBody();
    }

    @Override
    public String getFooter() {
        return email.getFooter();
    }
}
