package cardsystem.database;

@DynamoDBTable(tableName = "creditBureau")
public class CreditBureau {
  private Integer score;
  private String ssn;
  private <Integer> creditLines;
  
  @DynamoDBHashKey
  @DynamoDBAttribute
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }
  
  @DynamoDBAttribute
  public String getSsn() {
    return ssn;
  }
  public void setSsn(String ssn) {
    this.ssn = ssn;
  } 

  @DynamoDBAttribute
  public <Integer> getCreditLines() {
    return creditLines;
  }
  public void setCreditLines(<Integer> creditLines) {
    this.creditLines = creditLines;
  }
}
