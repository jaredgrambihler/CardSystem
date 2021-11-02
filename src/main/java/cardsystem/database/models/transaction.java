package cardsystem.database;

@DynamoDBTable(tableName = "Transaction")
public class Transaction {
  private String accountId;
  private String transactionId;
  private BigDecimal amount;
  private String transactionDate;
  private String postedDate;
  
  @DynamoDBHashKey(attributeName = "transactionId")
  @DynamoDBAttribute(attributeName = "accountId")
  public String getAccountId() {
    return accountId
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  
  @DynamoDBAttribute(attributeName = "transactionId")
  public String getTransactionId() {
    return transactionId;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  @DynamoDBAttribute(attributeName = "amount")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal accountNumber) {
    this.amount = amount;
  } 

  @DynamoDBAttribute(attributeName = "transactionDate")
  public String getTransactionDate() {
    return transactionDate;
  }
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  @DynamoDBAttribute(attributeName = "postedDate")
  public String getPostedDate() {
    return postedDate;
  }
  public void setPostedDate(String postedDate) {
    this.postedDate = postedDate;
  }
}
