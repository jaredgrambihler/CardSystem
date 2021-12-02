package cardsystem.rewards;

import cardsystem.transaction.Transaction;
import cardsystem.transaction.TransactionImpl;
import cardsystem.transaction.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RewardCalculatorTest {

    private List<Transaction> transactions;
    private int expectedPoints;
    private RewardCalculator rewardCalculator;

    @Parameterized.Parameters
    public static Collection getTransactions() {
        return Arrays.asList(new Object[][] {
                {
                    Arrays.asList(createMerchantTransaction("Burger King", 25.0)),
                        75
                },
                {
                    Arrays.asList(createMerchantTransaction("A general merchant", 20)),
                        30
                },
                {
                    Arrays.asList(createMerchantTransaction("Lidl", 100.5)),
                        201
                },
                {
                    Arrays.asList(createMerchantTransaction("British Airways", 500)),
                        2000
                },
                {
                    Arrays.asList(
                            createMerchantTransaction("Zara", 50),
                            createMerchantTransaction("Pizza Hut", 26),
                            createMerchantTransaction("Ryanair", 55),
                            createMerchantTransaction("Tesco", 40),
                            createMerchantTransaction("Random merchant", 6)
                    ),
                        125 + 78 + 220 + 80 + 9
                }
        });
    }

    public RewardCalculatorTest(List<Transaction> transactions, int expectedPoints) {
        this.transactions = transactions;
        this.expectedPoints = expectedPoints;
        this.rewardCalculator = new RewardCalculator();
    }

    @Test
    public void testCalculateRewardPoints() {
        int actual = rewardCalculator.calculateRewardPoints(transactions);
        assertEquals(expectedPoints, actual);
    }

    /**
     * Create a merchant transaction.
     * This method is used for testing to avoid saving to the db that occurs when transactions are typically created.
     * @return a merchant transaction
     */
    private static Transaction createMerchantTransaction(String merchant, double amount) {
        return new TransactionImpl("fakeId1234", "fakeAccountId1234", amount, merchant, LocalDateTime.now(), TransactionType.MERCHANT);
    }
}
