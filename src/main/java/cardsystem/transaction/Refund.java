package cardsystem.transaction;

import java.util.Date;
import java.util.Optional;

public class Refund extends Transaction {

    public Refund(String id, double amount, Date transactionDate, Optional<Date> postedDate) {
        super(id, amount, transactionDate, postedDate);
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.REFUND;
    }
}
