package cardsystem.transaction;

public enum TransactionType {
    CASH_ADVANCE("Cash_Advance", 0, Double.MAX_VALUE),
    MERCHANT("Merchant", 0, Double.MAX_VALUE),
    REFUND("Refund", -Double.MAX_VALUE, 0),
    PAYMENT("Payment", -Double.MAX_VALUE, 0);

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
