package cardsystem.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "User")
public class User {
  private String name;
  private String userId;
  private String birthDate;
  private Integer income;
  private String emailAddress;
  private String ssn;
  
  @DynamoDBHashKey(attributeName = "UserId")
  @DynamoDBAttribute
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  } 

  @DynamoDBAttribute(attributeName = "name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @DynamoDBAttribute(attributeName = "birthDate")
  public String getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  @DynamoDBAttribute(attributeName = "income")
  public Integer getIncome() {
    return income;
  }
  public void setIncome(Integer income) {
    this.income = income;
  } 

  @DynamoDBAttribute(attributeName = "emailAddress")
  public String getEmailAddress() {
    return emailAddress;
  }
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  } 

  @DynamoDBAttribute(attributeName = "ssn")
  public String getSsn() {
    return ssn;
  }
  public void setSsn(String ssn) {
    this.ssn = ssn;
  } 
}
