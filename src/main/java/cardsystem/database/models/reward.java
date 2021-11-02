package cardsystem.database;

@DynamoDBTable(tableName = "reward")
public class Reward {
  private String accountId;
  private String rewardPoints;
  
  @DynamoDBHashKey
  @DynamoDBAttribute
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId =accountId;
  }

  @DynamoDBAttribute
  public String getRewardPoints() {
    return rewardPoints;
  }
  public void setRewardPoints(String rewardPoints) {
    this.rewardPoints = rewardPoints;
  }
}
