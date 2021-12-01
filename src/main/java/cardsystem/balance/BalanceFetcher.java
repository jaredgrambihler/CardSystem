package cardsystem.balance;

import cardsystem.account.AccountFetcher;
import cardsystem.database.DynamoDBCommunicator;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.*;
import java.math.BigDecimal;

public class BalanceFetcher {
    public static Balance getLatestBalance(String accountId) {
        cardsystem.database.models.Balance balance = new cardsystem.database.models.Balance();
        balance.setAccountId(accountId);
        List<cardsystem.database.models.Balance> results = new DynamoDBCommunicator().query(
            cardsystem.database.models.Balance.class,
            new DynamoDBQueryExpression<cardsystem.database.models.Balance>()
                .withHashKeyValues(balance)
                .withScanIndexForward(true)
                .withLimit(1)
        );
        if (results.size() == 0) {

            return new Balance(accountId, BigDecimal.valueOf(0), BigDecimal.valueOf(AccountFetcher.loadCreditCardAccount(accountId).get().getCreditLimit()));
        } else {
            return loadBalance(results.get(0));
        }
    }
    
    public static Balance loadBalance(cardsystem.database.models.Balance balance) {
        return new Balance(balance.getAccountId(),
            balance.getBalance(),
            balance.getAvailableCredit()
        );
    }

}
