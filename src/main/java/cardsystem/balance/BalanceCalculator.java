package cardsystem.balance;

import java.math.BigDecimal;

public class BalanceCalculator implements Balance {
    BigDecimal balance;
    Double transaction;

    public BalanceCalculator(BigDecimal balance, Double transaction){
        this.balance = balance;
        this.transaction = transaction;
    }

    public BigDecimal checkBalance() {
        return balance;
    }

    public Double getTransaction() {
        return transaction;
    }

    public BigDecimal subtractTransaction(BigDecimal balance, Double transaction){
        balance.subtract(BigDecimal.valueOf(transaction)) ;
        //check if sufficient funds
        if(balance.compareTo(BigDecimal.valueOf(transaction)) == -1){
            //error handling
        }
        return balance;
    }

    protected cardsystem.database.models.Balance createDatabaseModel() {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        balance.setBalance(checkBalance());
        balance.setTransaction(getTransaction());
        return balance;
    }

}
