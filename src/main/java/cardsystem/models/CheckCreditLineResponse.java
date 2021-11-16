package cardsystem.models;

import java.util.List;

public class CheckCreditLineResponse {
    private String authToken;
    private double balance;
    private int creditLimit;
    private int availableCredit;

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
    
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public int getCreditLimit(){
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit){
        this.creditLimit = creditLimit;
    }

    public int getAvailableCredit(){
        return availableCredit;
    }

    public void getAvailableCredit(int availableCredit){
        this.availableCredit = availableCredit;
    }

}
