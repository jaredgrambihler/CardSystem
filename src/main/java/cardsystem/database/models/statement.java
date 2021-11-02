package cardsystem.database;

@DynamoDBTable(tableName = "statement")
public class Statement {
  private String accountId;
  private String rewards;
  private String balance;
  private String startDate;
  private String endDate;
  
  @DynamoDBHashKey
  @DynamoDBAttribute
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  
  @DynamoDBAttribute
  public String getRewards() {
    return rewards;
  }
  public void setRewards(String rewards) {
    this.rewards = rewards;
  }

  @DynamoDBAttribute
  public String getBalance() {
    return balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  } 

  @DynamoDBAttribute
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @DynamoDBAttribute
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
