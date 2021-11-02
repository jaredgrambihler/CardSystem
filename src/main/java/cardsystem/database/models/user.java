package cardsystem.database;

@DynamoDBTable(tableName = "User")
public class User {
  private String name;
  private String userId;
  private String birthDate;
  private Integer income;
  private String emailAddress;
  private String ssn;
  
  @DynamoDBHashKey(attributeName = "userId")
  @DynamoDBAttribute(attributeName = "name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @DynamoDBAttribute(attributeName = "UserId")
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
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
  public String getEmailAdress() {
    return emailAddress;
  }
  public void setEmailAdress(String emailAddress) {
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
