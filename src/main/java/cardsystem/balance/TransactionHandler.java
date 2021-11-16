package cardsystem.balance;

import java.math.BigDecimal;
import cardsystem.transaction.Transaction;

public class TransactionHandler {
    public void onTransaction(Transaction transaction) {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        if (transaction.getAccountId() == balance.getAccountId()) {
            BigDecimal currentAvailableCredit = balance.getAvailableCredit().subtract(BigDecimal.valueOf(transaction.getAmount()));
            balance.setBalance(currentAvailableCredit);
        } 
    }

    public void onTransactionPosted(Transaction transaction) {
        if (transaction.getPostedDate().isPresent()) {
            cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
            if (transaction.getAccountId() == balance.getAccountId()) {
                BigDecimal currentBalance = balance.getBalance().subtract(BigDecimal.valueOf(transaction.getAmount()));
                balance.setBalance(currentBalance);
            } 
        }
    } 
}
