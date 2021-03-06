package cardsystem.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Reward")
public class Reward {
  private String accountId;
  private Integer rewardPoints;
  
  @DynamoDBHashKey(attributeName = "accountId")
  @DynamoDBAttribute
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
