package cardsystem.models;

public class EmailNotificationsRequest {
    private String authToken;
    private String emailAddress;
    private String accountId;

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
    
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
