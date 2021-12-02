package cardsystem.creditbureau;

import cardsystem.database.DynamoDBCommunicator;

import java.util.List;

public class ExperianCreditReport implements CreditReport {
    String ssn;
    int score;
    int totalCreditLines;
    List<Integer> creditLines;

    public ExperianCreditReport (String ssn, int score, List<Integer> creditLines) {
        this.ssn = ssn;
        this.score = score;
        this.creditLines = creditLines;
    }

    public String getSSN() {
        return ssn;
    }

    public int getScore() {
        return score;
    }
    
    public int getTotalCreditLines() {
        for (int i : creditLines) {
            totalCreditLines += i;
        }
        return totalCreditLines;
    }

    public List<Integer> getCreditLines() {
        return creditLines;
    }

    public void saveToDatabase() {
        new DynamoDBCommunicator().save(createDatabaseModel());
    }

    protected cardsystem.database.models.CreditBureau createDatabaseModel() {
        cardsystem.database.models.CreditBureau experianCreditReport = new cardsystem.database.models.CreditBureau();
        experianCreditReport.setSsn(getSSN());
        experianCreditReport.setScore(getScore());
        experianCreditReport.setCreditLines(getCreditLines());
        return experianCreditReport;
    }
}
