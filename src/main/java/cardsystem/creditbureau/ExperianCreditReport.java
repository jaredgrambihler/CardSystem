package cardsystem.creditbureau;

public class ExperianCreditReport implements CreditReport {
    int score;
    String ssn;
    int totalCreditLines;
    List<Integer> creditLines;

    public ExperianCreditReport (int score, String ssn, int totalCreditLines) {
        this.score = score;
        this.ssn = ssn;
        this.totalCreditLines = totalCreditLines;
    }

    public int getScore() {
        return score;
    }

    public String getSSN() {
        return ssn;
    }
    
    public int getTotalCreditLines() {
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
    }
}
