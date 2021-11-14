package cardsystem.balance;

import cardsystem.database.DynamoDBCommunicator;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.*;

public class BalanceFetcher {
    public Optional<Balance> getLatestBalance(String accountId) {
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
            return Optional.empty();
        } else {
            return Optional.of(loadBalance(balance));
        }
    }
    
    public Balance loadBalance(cardsystem.database.models.Balance balance) {
        return new Balance(balance.getAccountId(),
                balance.getBalance()
        );
    }

    public Balance getAvailableCredit(cardsystem.database.models.Balance balance) {
        
    }

    public Balance getCreditLimit(cardsystem.database.models.Balance balance) {
       
    }
}


//get balance and creditlimit, available credit (all pending transactions)