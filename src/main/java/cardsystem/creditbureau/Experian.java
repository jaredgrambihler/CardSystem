package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    public Experian() {
    }

    public ExperianCreditReport getSoftInquiry(String ssn){
        // dummy values
        // TODO database check with 'creditReport'
        int score = 600;
        int creditLines = 10000;
        return new ExperianCreditReport(score, ssn, creditLines);    
    }

    public ExperianCreditReport getHardInquiry(String ssn){
        // dummy values
        // TODO database check with 'creditReport'
        int score = 600;
        int creditLines = 10000;
        return new ExperianCreditReport(score, ssn, creditLines);
    }
}
