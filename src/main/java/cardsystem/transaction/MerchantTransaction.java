package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class MerchantTransaction extends Transaction {

    private String merchant;

    public MerchantTransaction(String id, String accountId, double amount, LocalDateTime transactionDate, Optional<LocalDateTime> postedDate, String merchant) {
        super(id, accountId, amount, transactionDate, postedDate);
        this.merchant = merchant;
    }

    public String getMerchant() {
        return merchant;
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.MERCHANT;
    }

    @Override
    protected cardsystem.database.models.Transaction createDatabaseModel() {
        // TODO - create merchant transaction model
        return super.createDatabaseModel();
    }
}
