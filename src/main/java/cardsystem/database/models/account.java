package cardsystem.database;

@DynamoDBTable(tableName = "account")
public class Account {
  private String accountName;
  private String accountId;
  private String accountNumber;
  private String userId;
  
  @DynamoDBHashKey
  @DynamoDBAttribute
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accounrName = accountName;
  }
  
  @DynamoDBAttribute
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @DynamoDBAttribute
  public String getAccountNumber() {
    return accountNumber;
  }
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  } 

  @DynamoDBAttribute
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  } 
}
