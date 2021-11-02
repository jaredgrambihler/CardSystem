package cardsystem.database;

@DynamoDBTable(tableName = "transaction")
public class Transaction {
  private String accountId;
  private String transactionId;
  private Float amount;
  private String transactionDate;
  private String postedDate;
  
  @DynamoDBHashKey
  @DynamoDBAttribute
  public String getAccountId() {
    return accountId
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  
  @DynamoDBAttribute
  public String getTransactionId() {
    return transactionId;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  @DynamoDBAttribute
  public Float getAmount() {
    return amount;
  }
  public void setAmount(Float accountNumber) {
    this.amount = amount;
  } 

  @DynamoDBAttribute
  public String getTransactionDate() {
    return transactionDate;
  }
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  @DynamoDBAttribute
  public String getPostedDate() {
    return postedDate;
  }
  public void setPostedDate(String postedDate) {
    this.postedDate = postedDate;
  }
}
