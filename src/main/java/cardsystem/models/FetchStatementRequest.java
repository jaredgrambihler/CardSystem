package cardsystem.models;

public class FetchStatementRequest {
    private String authToken;
    private String accountId;
    private String date;


    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }
    
    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

}
