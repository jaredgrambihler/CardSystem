package cardsystem.database;

@DynamoDBTable(tableName = "Reward")
public class Reward {
  private String accountId;
  private Integer rewardPoints;
  
  @DynamoDBHashKey
  @DynamoDBAttribute(attributeName = "accountId")
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId =accountId;
  }

  @DynamoDBAttribute(attributeName = "rewardPoints")
  public Integer getRewardPoints() {
    return rewardPoints;
  }
  public void setRewardPoints(Integer rewardPoints) {
    this.rewardPoints = rewardPoints;
  }
}
