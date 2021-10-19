package cardsystem.transaction;

import java.util.Date;
import java.util.Optional;

public class CashAdvance extends Transaction {

    public CashAdvance(String id, double amount, Date transactionDate, Optional<Date> postedDate) {
        super(id, amount, transactionDate, postedDate);
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.CASH_ADVANCE;
    }
}
