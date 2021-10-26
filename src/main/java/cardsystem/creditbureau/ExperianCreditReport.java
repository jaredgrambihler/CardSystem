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
        // TODO database check
        return score;
    }

    public int getSSN() {
        // TODO database check
        return ssn;
    }
    
    public int getTotalCreditLines() {
        //TODO database check
        return creditLines;
    }
}
