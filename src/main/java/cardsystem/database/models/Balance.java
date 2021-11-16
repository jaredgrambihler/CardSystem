package cardsystem.database.models;

import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Balance")
public class Balance {
    private String accountId;
    private BigDecimal balance;
    private BigDecimal availableCredit;

    @DynamoDBHashKey(attributeName = "accountId")
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @DynamoDBAttribute(attributeName = "balance")
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @DynamoDBAttribute(attributeName = "availableCredit")
    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }
    
    public void setAvailableCredit(BigDecimal availableCredit) {
        this.availableCredit = availableCredit;
    }
}
