package cardsystem.creditbureau;

public class ExperianCreditReport implements CreditReport {
    int score;
    int ssn;
    int creditLines;

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
