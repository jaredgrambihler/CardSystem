package cardsystem.models;

public class MerchantTransactionResponse {
    private double balance;
    private boolean isApproved;
    
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}