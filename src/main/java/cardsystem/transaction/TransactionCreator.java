package cardsystem.transaction;

import java.util.Date;
import java.util.Optional;

public class TransactionCreator {

    public Transaction createCashAdvanceTransaction(double amount, Date transactionDate) {
        return new CashAdvance(createTransactionId(), amount, transactionDate, Optional.empty());
    }

    public Transaction createMerchantTransaction(double amount, Date transactionDate, String merchant) {
        return new MerchantTransaction(createTransactionId(), amount, transactionDate, Optional.empty(), merchant);
    }

    public Transaction createPayment(double amount, Date transactionDate) {
        return new Payment(createTransactionId(), amount, transactionDate, Optional.empty());
    }

    public Transaction createRefund(double amount, Date transactionDate) {
        return new Refund(createTransactionId(), amount, transactionDate, Optional.empty());
    }

    private String createTransactionId() {
        // TODO - complete implementation of id generator with database check
        return "abc";
    }
}
