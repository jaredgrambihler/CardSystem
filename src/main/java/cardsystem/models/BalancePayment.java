package cardsystem.models;

public class BalancePayment {
     private String id;
     private String accountId;
     private double amount;
     private String transactionDate;
     private String postedDate;

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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}
