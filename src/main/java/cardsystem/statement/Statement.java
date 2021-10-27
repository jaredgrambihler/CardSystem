package cardsystem.statement;

import cardsystem.transaction.Transaction;

import java.util.List;

public interface Statement {
    public double getBalance();
    public List<Transaction> getTransactions();
    public StatementPeriod getStatementPeriod();
}
