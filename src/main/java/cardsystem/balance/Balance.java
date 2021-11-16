package cardsystem.balance;

import cardsystem.database.DynamoDBCommunicator;

import java.math.BigDecimal;

public class Balance {
    private String accountId;
    private BigDecimal balance;
    private BigDecimal creditLimit;

    public Balance(String accountId, BigDecimal balance, BigDecimal creditLimit) {
        this.accountId = accountId;
        this.balance = balance;
        this.creditLimit = creditLimit;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void saveToDatabase() {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        balance.setAccountId(accountId);
        balance.setBalance(getBalance());
        balance.setCreditLimit(getCreditLimit());
        new DynamoDBCommunicator().save(balance);
    }
}
