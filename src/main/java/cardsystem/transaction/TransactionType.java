package cardsystem.transaction;

public enum TransactionType {
    CASH_ADVANCE("CASH_ADVANCE", 0, Double.MAX_VALUE),
    MERCHANT("MERCHANT", 0, Double.MAX_VALUE),
    REFUND("REFUND", -Double.MAX_VALUE, 0),
    PAYMENT("PAYMENT", -Double.MAX_VALUE, 0);

    private String transactionType;
    private double minAmount;
    private double maxAmount;

    TransactionType(String transactionType, double minAmount, double maxAmount) {
        this.transactionType = transactionType;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public boolean isValidAmount(double amount) {
        return amount >= minAmount && amount <= maxAmount;
    }

    @Override
    public String toString() {
        return transactionType;
    }

}
