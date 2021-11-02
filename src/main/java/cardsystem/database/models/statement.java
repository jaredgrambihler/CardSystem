package cardsystem.database;

@DynamoDBTable(tableName = "Statement")
public class Statement {
  private String accountId;
  private String rewards;
  private Integer balance;
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
  public String getRewards() {
    return rewards;
  }
  public void setRewards(String rewards) {
    this.rewards = rewards;
  }

  @DynamoDBAttribute(attributeName = "balance")
  public Integer getBalance() {
    return balance;
  }
  public void setBalance(Integer balance) {
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
