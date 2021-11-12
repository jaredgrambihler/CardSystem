package cardsystem.email;

public class CustomerEmail implements Email {

    private static final String FROM_ADDRESS = "CompanyMailingAddress@company.com";

    private String toAddress;
    private String subject;
    private String header;
    private String body;
    private String footer;

    public CustomerEmail(String toAddress, String subject, String header, String body, String footer) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    @Override
    public boolean userIsOptedIn() {
        return true;
    }

    @Override
    public String getToAddress() {
        return toAddress;
    }

    @Override
    public String getFromAddress() {
        return FROM_ADDRESS;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getFooter() {
        return footer;
    }

}
