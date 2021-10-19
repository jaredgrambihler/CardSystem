package cardsystem.transaction;

public enum TransactionType {
    CASH_ADVANCE("Cash Advance"),
    MERCHANT("Merchant"),
    REFUND("Refund"),
    PAYMENT("Payment");

    private String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return transactionType;
    }

}
