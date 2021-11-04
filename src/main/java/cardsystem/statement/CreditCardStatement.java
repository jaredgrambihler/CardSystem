package cardsystem.statement;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;
import cardsystem.transaction.Transaction;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class CreditCardStatement implements Statement {

    private String accountId;
    private double balance;
    private List<Transaction> transactions;
    private StatementPeriod statementPeriod;
    private int statementRewards;

    public CreditCardStatement(String accountId, double balance, List<Transaction> transactions, StatementPeriod statementPeriod, int statementRewards) {
        this.accountId = accountId;
        this.balance = balance;
        this.transactions = List.copyOf(transactions);
        this.statementPeriod = statementPeriod;
        this.statementRewards = statementRewards;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public StatementPeriod getStatementPeriod() {
        return statementPeriod;
    }

    @Override
    public void saveToDatabase() {
        cardsystem.database.models.Statement statement = new cardsystem.database.models.Statement();
        statement.setAccountId(accountId);
        statement.setBalance(BigDecimal.valueOf(getBalance()));
        statement.setStartDate(DateConverter.getIso8601Timestamp(statementPeriod.getStartDate().atStartOfDay()));
        statement.setEndDate(DateConverter.getIso8601Timestamp(statementPeriod.getEndDate().atStartOfDay()));
        statement.setRewards(getStatementRewards());
        new DynamoDBCommunicator().save(statement);
    }

    public int getStatementRewards() {
        return statementRewards;
    }
}
