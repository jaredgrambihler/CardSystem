package cardsystem.rewards;

public class RewardRedeemer {
	
	public void redeemPoints(int amount, String accountId) {
		// There would normally exist a UI to choose how to redeem the points (cash back, discounts etc)
		// For now, the total amount of points associated with an account will be decreased by the redeemed amount
		
		int currentRewards = RewardFetcher.getCurrentRewardPoints(accountId);
		if (amount <= currentRewards) {
			int newRewardAmount = currentRewards - amount;
			new RewardCalculator().updateRewardPointsInDatabase(accountId, newRewardAmount);
		}
	}
	
}
