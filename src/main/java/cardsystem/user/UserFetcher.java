package cardsystem.user;

import java.util.List;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;

public class UserFetcher {
	
	public static Optional<User> loadUser(String userId) {
        cardsystem.database.models.User queryModel = new cardsystem.database.models.User();
        queryModel.setUserId(userId);
        List<cardsystem.database.models.User> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.User.class,
                new DynamoDBQueryExpression<cardsystem.database.models.User>()
                        .withHashKeyValues(queryModel)
        );
        if (!results.isEmpty()) {
            cardsystem.database.models.User foundUser = results.get(0);
            User loadedUser = loadUserFromDatabaseModel(foundUser);
            if (loadedUser != null) {
                return Optional.of(loadedUser);
            }
        }
        return Optional.empty();
	}
	
	public static User loadUserFromDatabaseModel(cardsystem.database.models.User user) {
        return new User(user.getName(), user.getSsn(),
                user.getUserId(), user.getEmailAddress(), user.getIncome(), 
                DateConverter.getLocalDate(user.getBirthDate())
        );
	}

}
