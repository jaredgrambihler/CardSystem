package cardsystem.statement;

import cardsystem.transaction.Transaction;

import java.util.Collections;
import java.util.List;

public class CreditCardStatement implements Statement {

    private double balance;
    private List<Transaction> transactions;
    private StatementPeriod statementPeriod;
    private int statementRewards;

    public CreditCardStatement(double balance, List<Transaction> transactions, StatementPeriod statementPeriod, int statementRewards) {
        this.balance = balance;
        this.transactions = List.copyOf(transactions);
        this.statementPeriod = statementPeriod;
        this.statementRewards = statementRewards;
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

    public int getStatementRewards() {
        return statementRewards;
    }
}
