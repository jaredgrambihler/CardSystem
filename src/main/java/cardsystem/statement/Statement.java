package cardsystem.statement;

import cardsystem.transaction.Transaction;

import java.util.List;

public interface Statement {
    public String getAccountId();
    public double getBalance();
    public List<Transaction> getTransactions();
    public StatementPeriod getStatementPeriod();
    public void saveToDatabase();
}
