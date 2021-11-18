package cardsystem.balance;

import cardsystem.database.DynamoDBCommunicator;

import java.math.BigDecimal;

public class Balance {
    private String accountId;
    private BigDecimal balance;
    private BigDecimal availableCredit;

    public Balance(String accountId, BigDecimal balance, BigDecimal availableCredit) {
        this.accountId = accountId;
        this.balance = balance;
        this.availableCredit = availableCredit;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }

    public void updateAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }

    public void updateBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void saveToDatabase() {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        balance.setAccountId(accountId);
        balance.setBalance(getBalance());
        balance.setAvailableCredit(getAvailableCredit());
        new DynamoDBCommunicator().save(balance);
    }
}
