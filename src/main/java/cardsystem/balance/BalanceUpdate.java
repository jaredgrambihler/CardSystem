package cardsystem.balance;

import cardsystem.transaction.Transaction;

import java.math.BigDecimal;

public class BalanceUpdate {
    //takes transaction in account, adjust account balance based on transaction
    // argument is transaction, perform business logic to adjust database
    public void updateBalance(Transaction transaction) {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        if (transaction.getAccountId() == balance.getAccountId()) {
            BigDecimal currentBalance = balance.getBalance().subtract(BigDecimal.valueOf(transaction.getAmount()));
            balance.setBalance(currentBalance);
        } 
    }
}
