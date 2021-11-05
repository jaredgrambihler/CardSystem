package cardsystem.models;

public class AccountLogin {
    private String accountId;
    private String password;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    // not sure if password is needed or if it's a case of just using an accountId
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
