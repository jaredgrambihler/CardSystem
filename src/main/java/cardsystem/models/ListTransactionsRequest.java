package cardsystem.models;

public class ListTransactionsRequest {
    private String authToken;
    private String accountId;
    private String startTime;
    private String endTime;
    
    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStartTime(){
        return startTime;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public void setEnd(String endTime){
        this.endTime = endTime;
    }
}
