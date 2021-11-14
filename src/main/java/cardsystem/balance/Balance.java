package cardsystem.balance;

import cardsystem.database.DynamoDBCommunicator;

import java.math.BigDecimal;

public class Balance {
    private String accountId;
    private BigDecimal balance;

    public Balance(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void saveToDatabase() {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        balance.setAccountId(accountId);
        balance.setBalance(getBalance());
        new DynamoDBCommunicator().save(balance);
    }
}
