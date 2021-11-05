package cardsystem.models;

public class MakeStatement {
    private String accountId;
    private String statementPeriodStart;
    private String statementPeriodEnd;
    private double amount;
    private double balance;
    private String transactionId;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public String getStatementPeriodStart(){
        return statementPeriodStart;
    }

    public void setStatementPeriodStart(String statementPeriodStart){
        this.statementPeriodStart = statementPeriodStart;
    }

    public String getStatementPeriodEnd(){
        return statementPeriodEnd;
    }

    public void setStatementPeriodEnd(String statementPeriodEnd){
        this.statementPeriodEnd = statementPeriodEnd;
    }

    public String getTransactionId(){
        return transactionId;
    }

    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

}
