package cardsystem.database;

@DynamoDBTable(tableName = "Account")
public class Account {
  private String accountName;
  private String accountId;
  private String accountNumber;
  private String userId;
  
  @DynamoDBHashKey(attributeName = "accountId")
  @DynamoDBAttribute(attributeName = "accountName")
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
  
  @DynamoDBAttribute(attributeName = "accountId")
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @DynamoDBAttribute(attributeName = "accountNumber")
  public String getAccountNumber() {
    return accountNumber;
  }
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  } 

  @DynamoDBAttribute(attributeName = "userId")
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  } 
}
