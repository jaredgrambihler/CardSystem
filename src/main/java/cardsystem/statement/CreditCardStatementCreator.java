package cardsystem.statement;

import cardsystem.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CreditCardStatementCreator implements StatementCreator {

    @Override
    public CreditCardStatement createStatement(String accountId, StatementPeriod statementPeriod) {
        // TODO - fetch transactions and calculate balance and rewards from them
        int balance = 0;
        List<Transaction> transactions = new ArrayList<>();
        int statementRewards = 0;
        return new CreditCardStatement(balance, transactions, statementPeriod, statementRewards);
    }

}
