package cardsystem.rewards;

import java.util.List;
import java.util.Optional;

import cardsystem.database.DynamoDBCommunicator;
import cardsystem.transaction.Transaction;

public class RewardCalculator {
	private int rewardPoints;
	private String accountId;
	
	
	// Calculate and return reward points from list of transactions
	// If the account doesn't have any previous reward points, a new db reward object is created
	public int calculateRewardPoints(List<Transaction> transactions, String accountId) {
		int rewardPoints = 0;
		for (Transaction t: transactions) {
			rewardPoints += (int) Math.round(t.getAmount() * 2);
		}
		int previousRewardPoints = RewardFetcher.getCurrentRewardPoints(accountId);
		if (previousRewardPoints >= 0) {
			updateRewardPointsInDatabase(accountId, (rewardPoints + previousRewardPoints));
		} else {
			this.rewardPoints = rewardPoints;
			this.accountId = accountId;
			saveToDatabase();
		}
		return rewardPoints;
	}
	
	private void updateRewardPointsInDatabase(String accountId, int points) {
		Optional<cardsystem.database.models.Reward> reward = RewardFetcher.loadRewardDatabaseModel(accountId);
		if (reward.isPresent()) {
			cardsystem.database.models.Reward foundReward = reward.get();
			foundReward.setRewardPoints(points);
			new DynamoDBCommunicator().save(foundReward);
		}
	}
	
	public void saveToDatabase() {
		new DynamoDBCommunicator().save(createDatabaseModel());
	}

    /**
     * Create the database model.
     * @return database model object with fields populated
     */
    protected cardsystem.database.models.Reward createDatabaseModel() {
        cardsystem.database.models.Reward reward = new cardsystem.database.models.Reward();
        reward.setRewardPoints(rewardPoints);
        reward.setAccountId(accountId);
        return reward;
    }
}
