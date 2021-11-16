package cardsystem.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Account")
public class Account {
  private String accountName;
  private String accountId;
  private String accountNumber;
  private String userId;
  private int creditLimit;
  
  @DynamoDBHashKey(attributeName = "accountId")
  @DynamoDBAttribute
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @DynamoDBAttribute(attributeName = "accountName")
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accountName = accountName;
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
  
  @DynamoDBAttribute(attributeName = "creditLimit")
  public int getCreditLimit() {
    return creditLimit;
  }
  public void setCreditLimit(int creditLimit) {
    this.creditLimit = creditLimit;
  } 
}
