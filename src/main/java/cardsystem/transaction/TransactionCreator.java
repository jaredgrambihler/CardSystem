package cardsystem.transaction;

import cardsystem.balance.BalanceFetcher;
import cardsystem.balance.TransactionHandler;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class TransactionCreator {

    public Optional<Transaction> createTransaction(String accountId, double amount, String counterparty, TransactionType transactionType, LocalDateTime transactionDate) {
        if (BalanceFetcher.getLatestBalance(accountId).getAvailableCredit().compareTo(BigDecimal.valueOf(amount)) < 0) {
            // insufficient funds
            return Optional.empty();
        }
        // use decorator pattern to validate amount precondition in transactions
        // isValid will be False if amount isn't valid
        ValidatedTransaction transaction = new AmountValidatedTransaction(
                new TransactionImpl(createTransactionId(), accountId, amount,
                    counterparty, transactionDate, transactionType
                )
        );
        if (transaction.isValid()) {
            transaction.saveToDatabase();
            new TransactionHandler().onTransaction(transaction);
            return Optional.of(transaction);
        } else {
            return Optional.empty();
        }
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
