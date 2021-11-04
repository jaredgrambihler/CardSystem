package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class TransactionCreator {

    public Transaction createCashAdvanceTransaction(String accountId, double amount, LocalDateTime transactionDate) {
        Transaction transaction = new CashAdvance(createTransactionId(), accountId, amount, transactionDate, Optional.empty());
        transaction.saveToDatabase();
        return transaction;
    }

    public Transaction createMerchantTransaction(String accountId, double amount, LocalDateTime transactionDate, String merchant) {
        Transaction transaction = new MerchantTransaction(createTransactionId(), accountId, amount, transactionDate, Optional.empty(), merchant);
        transaction.saveToDatabase();
        return transaction;
    }

    public Transaction createPayment(String accountId, double amount, LocalDateTime transactionDate) {
        Transaction transaction = new Payment(createTransactionId(), accountId, amount, transactionDate, Optional.empty());
        transaction.saveToDatabase();
        return transaction;
    }

    public Transaction createRefund(String accountId, double amount, LocalDateTime transactionDate) {
        Transaction transaction = new Refund(createTransactionId(), accountId, amount, transactionDate, Optional.empty());
        transaction.saveToDatabase();
        return transaction;
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
