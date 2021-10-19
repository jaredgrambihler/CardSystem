package cardsystem.transaction;

import java.util.Date;
import java.util.Optional;

public abstract class Transaction {

    private String id;
    private double amount;
    private Date transactionDate;
    private Optional<Date> postedDate;

    /**
     * Create a transaction.
     * @param id unique id of the transaction
     * @param amount the amount of the transaction
     * @param transactionDate the date the transaction occurred
     * @param postedDate the date the transaction posted, which is empty if the transaction has not yet posted
     */
    public Transaction(String id, double amount, Date transactionDate, Optional<Date> postedDate) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.postedDate = postedDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getID() {
        return id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Optional<Date> getPostedDate() {
        return postedDate;
    }

    /**
     * Set the posted date of a transaction.
     * @param postedDate the date the transaction posted
     * @throws IllegalArgumentException if postedDate is before transactionDate
     */
    public void setPostedDate(Date postedDate) throws IllegalArgumentException {
        if(postedDate.before(transactionDate)) {
            throw new IllegalArgumentException("Posted date cannot be before transaction date");
        }
        this.postedDate = Optional.of(postedDate);
    }

    public abstract TransactionType getTransactionType();

}
