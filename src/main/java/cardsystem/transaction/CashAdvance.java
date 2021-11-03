package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class CashAdvance extends Transaction {

    public CashAdvance(String id, String accountId, double amount, LocalDateTime transactionDate, Optional<LocalDateTime> postedDate) {
        super(id, accountId, amount, transactionDate, postedDate);
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.CASH_ADVANCE;
    }
}
