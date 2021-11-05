package cardsystem.models;

public class BalanceCheck {
    private String accountId;
    private double balance;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

}
