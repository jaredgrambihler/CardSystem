package cardsystem.models;

import java.time.LocalDateTime;

public class listTransactions {
    private String accountId;
    private String transactionId;
    private LocalDateTime transactionDate;
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

    public LocalDateTime getTransactionDate(){
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate){
        this.transactionDate = transactionDate;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}
