package cardsystem.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "Transaction")
public class Transaction {
  private String accountId;
  private String transactionId;
  private BigDecimal amount;
  private String transactionDate;
  private String postedDate;
  private String transactionType;
  private String counterparty;
  
  @DynamoDBHashKey(attributeName = "transactionId")
  @DynamoDBAttribute
  public String getTransactionId() {
    return transactionId;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  @DynamoDBIndexHashKey(attributeName = "accountId", globalSecondaryIndexName = "accountIndex")
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @DynamoDBAttribute(attributeName = "amount")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
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

  @DynamoDBAttribute(attributeName = "transactionType")
  public String getTransactionType() {
    return transactionType;
  }
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  @DynamoDBAttribute(attributeName = "counterparty")
  public String getCounterparty() {
    return counterparty;
  }
  public void setCounterparty(String counterparty) {
    this.counterparty = counterparty;
  }

}
