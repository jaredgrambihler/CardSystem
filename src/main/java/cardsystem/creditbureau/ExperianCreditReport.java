package cardsystem.creditbureau;

public class ExperianCreditReport implements CreditReport {
    int score;
    String ssn;
    int creditLines;

    public ExperianCreditReport (int score, String ssn, int creditLines) {
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
        return creditLines;
    }
}
