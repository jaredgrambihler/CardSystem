package cardsystem.models;

public class AccountCreation {
    private String userId;
    private String accountName;
    private String accountNumber;
    private String accountId;

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNr(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }
}
