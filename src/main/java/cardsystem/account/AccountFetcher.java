package cardsystem.account;

import java.util.List;
import java.util.Optional;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import cardsystem.database.DynamoDBCommunicator;

public class AccountFetcher {

	public static Optional<CreditCardAccount> loadCreditCardAccount(String accountId) {
        cardsystem.database.models.Account queryModel = new cardsystem.database.models.Account();
        queryModel.setAccountId(accountId);
        List<cardsystem.database.models.Account> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Account.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Account>()
                        .withHashKeyValues(queryModel)
        );
        if (!results.isEmpty()) {
            cardsystem.database.models.Account foundAccount = results.get(0);
            CreditCardAccount loadedAccount = loadCreditCardAccountFromDatabaseModel(foundAccount);
            if (loadedAccount != null) {
                return Optional.of(loadedAccount);
            }
        }
        return Optional.empty();
    }
	
	public static CreditCardAccount loadCreditCardAccountFromDatabaseModel(cardsystem.database.models.Account account) {
        return new CreditCardAccount(account.getAccountName(), account.getAccountId(),
        		account.getAccountNumber(), account.getUserId()
        );
	}
	
	public static Optional<cardsystem.database.models.Account> loadAccountDatabaseModel(String accountId) {
		cardsystem.database.models.Account queryModel = new cardsystem.database.models.Account();
        queryModel.setAccountId(accountId);
        List<cardsystem.database.models.Account> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Account.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Account>()
                        .withHashKeyValues(queryModel)
        );
        if (!results.isEmpty()) {
            cardsystem.database.models.Account foundAccount = results.get(0);
            return Optional.of(foundAccount);
        }
        return Optional.empty();
	}
	
}
	

