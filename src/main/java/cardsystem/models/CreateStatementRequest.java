package cardsystem.models;

import cardsystem.statement.StatementPeriod;

public class CreateStatementRequest {
    private String accountId;
    private StatementPeriod statementPeriod;
    private String authToken;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public StatementPeriod getStatementPeriod(){
        return statementPeriod;
    }

    public void setStatementPeriod(StatementPeriod statementPeriod){
        this.statementPeriod = statementPeriod;
    }

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

}
