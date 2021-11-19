package cardsystem.transaction;

public class PositiveTransaction extends ValidatedTransaction {

    public PositiveTransaction(Transaction transaction) throws IllegalArgumentException {
        super(transaction);
    }

    @Override
    public boolean isValid() {
        return super.isValid() && getTransaction().getAmount() >= 0;
    }
}
