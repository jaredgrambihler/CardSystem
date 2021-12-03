package cardsystem.transaction;

public class AmountValidatedTransaction extends ValidatedTransaction {

    public AmountValidatedTransaction(Transaction transaction) {
        super(transaction);
    }

    @Override
    public boolean isValid() {
        Transaction transaction = getTransaction();
        return super.isValid() && transaction.getTransactionType().isValidAmount(transaction.getAmount());
    }
}
