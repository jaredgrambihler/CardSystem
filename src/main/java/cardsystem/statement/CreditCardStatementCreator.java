package cardsystem.statement;

import cardsystem.rewards.RewardCalculator;
import cardsystem.transaction.Transaction;
import cardsystem.transaction.TransactionFetcher;

import java.util.List;
import java.util.Optional;

public class CreditCardStatementCreator implements StatementCreator {

    @Override
    public CreditCardStatement createStatement(String accountId, StatementPeriod statementPeriod) {
        Optional<Statement> latestStatement = new CreditCardStatementFetcher().getLatestStatement(accountId);
        double balance = 0;
        if (latestStatement.isPresent()) {
            balance = latestStatement.get().getBalance();
        }
        List<Transaction> transactions = TransactionFetcher.loadPostedTransactions(
                accountId,
                statementPeriod.getStartDate().atStartOfDay(),
                statementPeriod.getStartDate().atStartOfDay()
        );
        for (Transaction transaction: transactions) {
            balance += transaction.getAmount();
        }
        int statementRewards = new RewardCalculator().createRewardPoints(transactions, accountId);
        CreditCardStatement statement = new CreditCardStatement(accountId, balance, transactions, statementPeriod, statementRewards);
        statement.saveToDatabase();
        return statement;
    }

}
