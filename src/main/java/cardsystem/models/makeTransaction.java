package cardsystem.models;

import java.time.LocalDateTime;

public class makeTransaction {
    private String accountId;
    private double amount;
    private LocalDateTime transactionDate;
    private String merchant;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public String getMerchant(){
        return merchant;
    }

    public void setMerchant(String merchant){
        this.merchant = merchant;
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
