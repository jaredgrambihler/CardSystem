package cardsystem.models;

import java.time.LocalDateTime;

public class balancePayment {
     private String id;
     private String accountId;
     private double amount;
     private LocalDateTime transactionDate;
     private LocalDateTime postedDate;

     public String getId(){
         return id;
     }

    public void setId(String id) {
        this.id = id;
    }

    public String accountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }
}
