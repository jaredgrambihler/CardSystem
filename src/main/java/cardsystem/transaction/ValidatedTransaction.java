package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class ValidatedTransaction implements Transaction {

    private Transaction transaction;

    public ValidatedTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean isValid() {
        return true;
    }

    protected Transaction getTransaction() {
        return transaction;
    }

    @Override
    public void saveToDatabase() {
        transaction.saveToDatabase();
    }

    @Override
    public double getAmount() {
        return transaction.getAmount();
    }

    @Override
    public String getTransactionId() {
        return transaction.getTransactionId();
    }

    @Override
    public String getAccountId() {
        return transaction.getAccountId();
    }

    @Override
    public String getCounterparty() {
        return transaction.getCounterparty();
    }

    @Override
    public LocalDateTime getTransactionDate() {
        return transaction.getTransactionDate();
    }

    @Override
    public boolean isPosted() {
        return transaction.isPosted();
    }

    @Override
    public Optional<LocalDateTime> getPostedDate() {
        return transaction.getPostedDate();
    }

    @Override
    public TransactionType getTransactionType() {
        return transaction.getTransactionType();
    }
}
