package cardsystem.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "CreditBureau")
public class CreditBureau {
  private String ssn;
  private Integer score;
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
  public List<Integer> getCreditLines() {
    return creditLines;
  }
  public void setCreditLines(List<Integer> creditLines) {
    this.creditLines = creditLines;
  }
}
