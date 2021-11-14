package cardsystem.balance;

import cardsystem.statement.StatementPeriod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BalanceCreator {

    public Balance balanceCreator(String accountId, StatementPeriod statementPeriod){
        Optional<Balance> latestBalance = new BalanceCreator().
        BigDecimal balance = 0; 
        if (latestBalance.isPresent()) {
            balance = latestBalance.get().getBalance();
        }
        List<Balance> balances = BalanceFetcher.loadPostedBalance(
            accountId,
            statementPeriod.getStartDate().atStartOfDay(),
            statementPeriod.getStartDate().atStartOfDay()
        );
        for (Transaction transaction: transactions) {
            balance += transaction.getAmount();
        }

        Balance balance = new Balance();
        balance.saveToDatabase();
        return balance;
    }


    protected cardsystem.database.models.Balance createDatabaseModel() {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        balance.setBalance(getBalance());
        return balance;
    }

}



