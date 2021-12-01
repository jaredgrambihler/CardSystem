package cardsystem.transaction;

import cardsystem.balance.TransactionHandler;
import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionImpl implements Transaction {

    private String transactionId;
    private String accountId;
    private double amount;
    private String counterparty;
    private LocalDateTime transactionDate;
    private PostedState postedState;
    private TransactionType transactionType;

    /**
     * Create a transaction.
     * @param transactionId unique id of the transaction
     * @param accountId the account that made the transaction
     * @param amount the amount of the transaction
     * @param counterparty the counterparty of the transaction, such as the merchant or bank on the other side
     * @param transactionDate the date the transaction occurred
     * @param transactionType the type of transaction
     */
    public TransactionImpl(String transactionId, String accountId, double amount, String counterparty,
                           LocalDateTime transactionDate, TransactionType transactionType) {
        this(transactionId, accountId, amount, counterparty, transactionDate, transactionType, null);
    }

    /**
     * Create a transaction.
     * @param transactionId unique id of the transaction
     * @param accountId the account that made the transaction
     * @param amount the amount of the transaction
     * @param counterparty the counterparty of the transaction, such as the merchant or bank on the other side
     * @param transactionDate the date the transaction occurred
     * @param transactionType the type of transaction
     * @param postedDate the posted date of the transaction, or null if it hasn't posted
     */
    public TransactionImpl(String transactionId, String accountId, double amount, String counterparty,
                           LocalDateTime transactionDate, TransactionType transactionType,
                           LocalDateTime postedDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.counterparty = counterparty;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        if (postedDate == null) {
            this.postedState = new PendingTransactionState();
        } else {
            this.postedState = new PostedTransactionState(postedDate);
        }
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public String getCounterparty() {
        return counterparty;
    }

    @Override
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public boolean isPosted() {
        return postedState.isPosted();
    }

    @Override
    public Optional<LocalDateTime> getPostedDate() {
        return postedState.getPostedDate();
    }

    @Override
    public TransactionType getTransactionType() {
        return transactionType;
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
        this.postedState = new PostedTransactionState(postedDate);
        new TransactionHandler().onTransactionPosted(this);
    }

    public void saveToDatabase() {
        new DynamoDBCommunicator().save(createDatabaseModel());
    }

    /**
     * Create the database model.
     * @return database model object with fields populated
     */
    private cardsystem.database.models.Transaction createDatabaseModel() {
        cardsystem.database.models.Transaction transaction = new cardsystem.database.models.Transaction();
        transaction.setTransactionId(getTransactionId());
        transaction.setAccountId(getAccountId());
        transaction.setCounterparty(getCounterparty());
        transaction.setTransactionDate(DateConverter.getIso8601Timestamp(getTransactionDate()));
        if (isPosted()) {
            transaction.setPostedDate(DateConverter.getIso8601Timestamp(getPostedDate().get()));
        } else {
            transaction.setPostedDate(null);
        }
        transaction.setAmount(BigDecimal.valueOf(getAmount()));
        transaction.setTransactionType(getTransactionType().toString());
        return transaction;
    }
}
