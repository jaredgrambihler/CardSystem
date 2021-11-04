package cardsystem.transaction;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public abstract class Transaction {

    private String transactionId;
    private String accountId;
    private double amount;
    private LocalDateTime transactionDate;
    private Optional<LocalDateTime> postedDate;

    /**
     * Create a transaction.
     * @param transactionId unique id of the transaction
     * @param accountId the account that made the transaction
     * @param amount the amount of the transaction
     * @param transactionDate the date the transaction occurred
     * @param postedDate the date the transaction posted, which is empty if the transaction has not yet posted
     */
    public Transaction(String transactionId, String accountId, double amount, LocalDateTime transactionDate, Optional<LocalDateTime> postedDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.postedDate = postedDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Optional<LocalDateTime> getPostedDate() {
        return postedDate;
    }

    /**
     * Set the posted date of a transaction.
     * @param postedDate the date the transaction posted
     * @throws IllegalArgumentException if postedDate is before transactionDate
     */
    public void setPostedDate(LocalDateTime postedDate) throws IllegalArgumentException {
        if(postedDate.isBefore(transactionDate)) {
            throw new IllegalArgumentException("Posted date cannot be before transaction date");
        }
        this.postedDate = Optional.of(postedDate);
    }

    public void saveToDatabase() {
        new DynamoDBCommunicator().save(createDatabaseModel());
    }

    /**
     * Create the database model.
     * @return database model object with fields populated
     */
    protected cardsystem.database.models.Transaction createDatabaseModel() {
        cardsystem.database.models.Transaction transaction = new cardsystem.database.models.Transaction();
        transaction.setTransactionId(getTransactionId());
        transaction.setAccountId(getAccountId());
        transaction.setTransactionDate(DateConverter.getIso8601Timestamp(getTransactionDate()));
        if (postedDate.isPresent()) {
            transaction.setPostedDate(DateConverter.getIso8601Timestamp(getPostedDate().get()));
        } else {
            transaction.setPostedDate(null);
        }
        transaction.setAmount(BigDecimal.valueOf(getAmount()));
        transaction.setTransactionType(getTransactionType().toString());
        return transaction;
    }

    public abstract TransactionType getTransactionType();

}
