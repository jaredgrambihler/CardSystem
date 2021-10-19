package cardsystem.transaction;

import java.util.Date;
import java.util.Optional;

public class MerchantTransaction extends Transaction {

    private String merchant;

    public MerchantTransaction(String id, double amount, Date transactionDate, Optional<Date> postedDate, String merchant) {
        super(id, amount, transactionDate, postedDate);
        this.merchant = merchant;
    }

    public String getMerchant() {
        return merchant;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.MERCHANT;
    }
}
