package cardsystem.email;

import cardsystem.account.Account;

public class AccountEmailDecorator extends EmailDecorator {

    private Account account;

    public AccountEmailDecorator(Email email, Account account) {
        super(email);
        this.account = account;
    }

    @Override
    public String getHeader() {
        return super.getHeader() + "/n" +
                "This email is regarding the following account: " +
                account.getAccountName();
    }

    @Override
    public String getFooter() {
        return super.getFooter() + "\n" +
                "If you are not the owner of this account, please notify us immediately and delete this email from your system.";
    }
}
