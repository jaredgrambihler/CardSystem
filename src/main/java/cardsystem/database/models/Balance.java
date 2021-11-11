package cardsystem.database.models;

import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Balance")
public class Balance {
    private BigDecimal balance;
    private double transaction;

    @DynamoDBHashKey(attributeName = "balance")
    @DynamoDBAttribute
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @DynamoDBAttribute(attributeName = "transaction")
    public Double getTransaction() {
        return transaction;
    }
    public void setTransaction(Double transaction) {
        this.transaction = transaction;
    }
}
