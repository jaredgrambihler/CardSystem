package cardsystem.rewards;

import java.util.List;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import cardsystem.database.DynamoDBCommunicator;

public class RewardFetcher {
	
	public static Optional<cardsystem.database.models.Reward> loadRewardDatabaseModel(String accountId) {
		cardsystem.database.models.Reward queryModel = new cardsystem.database.models.Reward();
        queryModel.setAccountId(accountId);
        List<cardsystem.database.models.Reward> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Reward.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Reward>()
                        .withHashKeyValues(queryModel)
        );
        if (!results.isEmpty()) {
            cardsystem.database.models.Reward foundReward = results.get(0);
            return Optional.of(foundReward);
        }
        return Optional.empty();
	}
	
	public static int getCurrentRewardPoints(String accountId) {
		Optional<cardsystem.database.models.Reward> reward = RewardFetcher.loadRewardDatabaseModel(accountId);
		if (reward.isPresent()) {
			return reward.get().getRewardPoints();
		}
        return -1;
	}

}
