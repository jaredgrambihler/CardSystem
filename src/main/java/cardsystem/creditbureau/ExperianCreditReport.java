package cardsystem.creditbureau;

import java.util.List;

public class ExperianCreditReport implements CreditReport {
    int score;
    String ssn;
    int totalCreditLines;
    List<Integer> creditLines;

    public ExperianCreditReport (int score, String ssn, List<Integer> creditLines) {
        this.score = score;
        this.ssn = ssn;
        this.creditLines = creditLines;
    }

    public int getScore() {
        return score;
    }

    public String getSSN() {
        return ssn;
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

    protected cardsystem.database.models.CreditBureau createDatabaseModel() {
        cardsystem.database.models.CreditBureau experianCreditReport = new cardsystem.database.models.CreditBureau();
        experianCreditReport.setSsn(getSSN());
        experianCreditReport.setScore(getScore());
        experianCreditReport.setCreditLines(getCreditLines());
        return experianCreditReport;
    }
}
