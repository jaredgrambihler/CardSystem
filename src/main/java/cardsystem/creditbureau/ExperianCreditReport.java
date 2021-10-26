package cardsystem.creditbureau;

public class ExperianCreditReport implements CreditReport {
    int score;
    int ssn;
    int creditLines;

    public ExperianCreditReport (int score, int ssn, int creditLines) {
        this.score = score;
        this.ssn = ssn;
        this.creditLines = creditLines;
    }

    public int getScore() {
        return score;
    }

    public int getSSN() {
        return ssn;
    }
    
    public int getTotalCreditLines() {
        return creditLines;
    }
}
