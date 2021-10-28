package cardsystem.rewards;

public class RewardRedeemer {
	
	public void redeemPoints(int amount, String accountId) {
		// TODO - implement with db (decrease total amount of points by amount, redeem cash back for statement credit?)
		int cashBack = (int) Math.round(0.01 * amount);
	}
	
}
