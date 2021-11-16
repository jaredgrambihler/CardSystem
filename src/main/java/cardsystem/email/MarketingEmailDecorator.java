package cardsystem.email;

public class MarketingEmailDecorator extends EmailDecorator {

    private boolean isOptedIn;

    public MarketingEmailDecorator(Email email, boolean isOptedIn) {
        super(email);
        this.isOptedIn = isOptedIn;
    }

    @Override
    public boolean userIsOptedIn() {
        return isOptedIn;
    }

    @Override
    public String getFooter() {
        return super.getFooter() + "\n" +
                "You can unsubscribe from marketing emails at any time by logging into your account and opting out.";
    }
}
