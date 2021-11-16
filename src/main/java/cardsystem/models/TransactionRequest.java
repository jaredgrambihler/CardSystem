package cardsystem.models;

public class TransactionRequest {

    private String authToken;
    private String accountId;
    private String counterparty;
    private double amount;

    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCounterparty() {
        return counterparty;
    }
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }
}
