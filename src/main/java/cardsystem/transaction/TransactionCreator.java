package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class TransactionCreator {

    public Optional<Transaction> createTransaction(String accountId, double amount, String counterparty, TransactionType transactionType, LocalDateTime transactionDate) {
        if (!transactionType.isValidAmount(amount)) {
            return Optional.empty();
        }
        // TODO - check account balance is sufficient
        Transaction transaction = new TransactionImpl(createTransactionId(), accountId, amount,
                counterparty, transactionDate, Optional.empty(), transactionType);
        transaction.saveToDatabase();
        return Optional.of(transaction);
    }

    private String createTransactionId() {
        // create unique ID's until one is created without collision
        // collisions should rarely, if ever, occur
        while(true) {
            String transactionId = UUID.randomUUID().toString();
            if (TransactionFetcher.loadTransaction(transactionId).isEmpty()) {
                return transactionId;
            }
        }
    }
}
