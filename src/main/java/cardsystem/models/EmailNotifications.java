package cardsystem.models;

public class EmailNotifications {
    private String emailAddress;
    private String accountId;

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAccountId(){
        return accountId;
    }

    public void setACcountId(String accountId) {
        this.accountId = accountId;
    }

}