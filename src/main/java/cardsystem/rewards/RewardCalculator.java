package cardsystem.rewards;

import java.util.List;
import java.util.Optional;

import cardsystem.database.DynamoDBCommunicator;
import cardsystem.transaction.MerchantTransaction;
import cardsystem.transaction.Transaction;

public class RewardCalculator {
	
	// Calculate and return reward points from list of transactions
	// If the account doesn't have any previous reward points, a new db reward object is created
	public int createRewardPoints(List<Transaction> transactions, String accountId) {
		int rewardPoints = calculateRewardPoints(transactions);
		int previousRewardPoints = RewardFetcher.getCurrentRewardPoints(accountId);
		if (previousRewardPoints >= 0) {
			updateRewardPointsInDatabase(accountId, (rewardPoints + previousRewardPoints));
		} else {
			saveToDatabase(rewardPoints, accountId);
		}
		return rewardPoints;
	}
	
	public int calculateRewardPoints(List<Transaction> transactions) {
		int rewardPoints = 0;
		for (Transaction t: transactions) {
			if (t instanceof MerchantTransaction) {
				MerchantTransaction merchantTransaction = (MerchantTransaction) t;
				String merchant = merchantTransaction.getMerchant();
				MerchantCategory category = MerchantCategory.getCategory(merchant);
				double rewardMultiplier;
				switch(category) {
					case RESTAURANT:
						rewardMultiplier = 3;
						break;
					case CLOTHING_STORE:
						rewardMultiplier = 2.5;
						break;
					case GROCERY_STORE:
						rewardMultiplier = 2;
						break;
					case AIRLINE:
						rewardMultiplier = 4;
						break;
					default:
						rewardMultiplier = 1.5;
				}
				rewardPoints += (int) Math.round(merchantTransaction.getAmount() * rewardMultiplier);
			}
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
	
	public void saveToDatabase(int rewardPoints, String accountId) {
		new DynamoDBCommunicator().save(createDatabaseModel(rewardPoints, accountId));
	}

    /**
     * Create the database model.
     * @return database model object with fields populated
     */
    protected cardsystem.database.models.Reward createDatabaseModel(int rewardPoints, String accountId) {
        cardsystem.database.models.Reward reward = new cardsystem.database.models.Reward();
        reward.setRewardPoints(rewardPoints);
        reward.setAccountId(accountId);
        return reward;
    }
}
