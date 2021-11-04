package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class Refund extends Transaction {

    public Refund(String id, String accountId, double amount, LocalDateTime transactionDate, Optional<LocalDateTime> postedDate) {
        super(id, accountId, amount, transactionDate, postedDate);
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.REFUND;
    }
}
