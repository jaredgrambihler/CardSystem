package cardsystem.rewards;

import java.util.List;
import cardsystem.transaction.Transaction;

public class RewardCalculator {
	
	public int calculateRewardPoints(List<Transaction> transactions) {
		int points = 0;
		for (Transaction t: transactions) {
			points += (int) Math.round(t.getAmount() * 2);
		}
		return points;
	}
	
}
