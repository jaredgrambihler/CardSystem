package cardsystem.models;

public class CreateStatementRequest {
    private String accountId;
    private String statementPeriodEnd;
    private String authToken;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public String getStatementPeriodEnd(){
        return statementPeriodEnd;
    }

    public void setStatementPeriodEnd(String statementPeriodEnd){
        this.statementPeriodEnd = statementPeriodEnd;
    }

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

}
