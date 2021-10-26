package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    ExperianCreditReport softInquiry;
    ExperianCreditReport hardInquiry;

    public Experian() {
    }

    public ExperianCreditReport getSoftInquiry(int ssn){
        // dummy values
        int score = 600;
        int creditLines = 10000;
        CreditReport creditReport = new ExperianCreditReport(score, ssn, creditLines)
        // TODO database check with 'creditReport'
        return softInquiry;
    }

    public ExperianCreditReport getHardInquiry(int ssn){
        // dummy values
        int score = 600;
        int creditLines = 10000;
        CreditReport creditReport = new ExperianCreditReport(score, ssn, creditLines)
        
        // TODO database check with 'creditReport'
        return hardInquiry;
    }
}
