package cardsystem.models;

public class MakeTransaction {
    private String accountId;
    private double amount;
    private String transactionDate;
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
