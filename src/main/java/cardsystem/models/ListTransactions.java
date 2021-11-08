package cardsystem.models;

public class ListTransactions { 
    private String accountId;
    private String transactionId;
    private String transactionDate;
    private double amount;


    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public String getTransactionId(){
        return transactionId;
    }

    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }

    public String getTransactionDate(){
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate){
        this.transactionDate = transactionDate;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}
