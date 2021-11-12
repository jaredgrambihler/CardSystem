package cardsystem.email;

import cardsystem.account.Account;

public class EmailFactory {

    public Email getCustomerEmail(String toAddress, String subject, String body) {
        return new CustomerEmail(toAddress, subject, "", body, "");
    }

    public Email getAccountEmail(Account account, String toAddress, String subject, String body) {
        return new AccountEmailDecorator(getCustomerEmail(toAddress, subject, body), account);
    }

    public Email getAccountMarketingEmail(Account account, String toAddress, String subject, String body) {
        boolean isOptedIn = true; // in an actual system, we would need to contact a marketing package to see if a user is opted in
        return new MarketingEmailDecorator(getAccountEmail(account, toAddress, subject, body), isOptedIn);
    }
}
