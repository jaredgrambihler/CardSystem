package cardsystem.transaction;

import java.util.Date;
import java.util.Optional;

public class Payment extends Transaction {

    public Payment(String id, double amount, Date transactionDate, Optional<Date> postedDate) {
        super(id, amount, transactionDate, postedDate);
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.PAYMENT;
    }
}
