package cardsystem.database;

@DynamoDBTable(tableName = "user")
public class User {
  private String name;
  private String userId;
  private String birthDate;
  private Integer income;
  private String emailAddress;
  private String ssn;
  
  @DynamoDBHashKey
  @DynamoDBAttribute
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @DynamoDBAttribute
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  } 
  
  @DynamoDBAttribute
  public String getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  @DynamoDBAttribute
  public Integer getIncome() {
    return income;
  }
  public void setIncome(Integer income) {
    this.income = income;
  } 

  @DynamoDBAttribute
  public String getEmailAdress() {
    return emailAddress;
  }
  public void setEmailAdress(String emailAddress) {
    this.emailAddress = emailAddress;
  } 

  @DynamoDBAttribute
  public String getSsn() {
    return ssn;
  }
  public void setSsn(String ssn) {
    this.ssn = ssn;
  } 
}
