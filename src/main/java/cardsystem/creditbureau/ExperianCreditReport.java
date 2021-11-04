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
        this.totalCreditLines = totalCreditLines.sum(creditLines);
    }

    public int getScore() {
        return score;
    }

    public String getSSN() {
        return ssn;
    }
    
    public int getTotalCreditLines(List<Integer> list) {
        for (int i : list) {
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
