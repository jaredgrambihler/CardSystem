package cardsystem.database;

@DynamoDBTable(tableName = "CreditBureau")
public class CreditBureau {
  private Integer score;
  private String ssn;
  private List<Integer> creditLines;
  
  @DynamoDBHashKey(attributeName = "ssn")
  @DynamoDBAttribute
  public String getSsn() {
    return ssn;
  }
  public void setSsn(String ssn) {
    this.ssn = ssn;
  } 

  @DynamoDBAttribute(attributeName = "score")
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }

  @DynamoDBAttribute(attributeName = "creditLines")
  public <Integer> getCreditLines() {
    return creditLines;
  }
  public void setCreditLines(<Integer> creditLines) {
    this.creditLines = creditLines;
  }
}
