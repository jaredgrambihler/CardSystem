package cardsystem.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "Statement")
public class Statement {
  private String accountId;
  private int rewards;
  private BigDecimal balance;
  private String startDate;
  private String endDate;
  
  @DynamoDBHashKey(attributeName = "accountId")
  @DynamoDBAttribute
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  
  @DynamoDBAttribute(attributeName = "rewards")
  public int getRewards() {
    return rewards;
  }
  public void setRewards(int rewards) {
    this.rewards = rewards;
  }

  @DynamoDBAttribute(attributeName = "balance")
  public BigDecimal getBalance() {
    return balance;
  }
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  } 

  @DynamoDBAttribute(attributeName = "startDate")
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @DynamoDBAttribute(attributeName = "endDate")
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
