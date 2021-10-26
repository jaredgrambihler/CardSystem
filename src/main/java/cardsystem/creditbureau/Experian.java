package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    ExperianCreditReport softInquiry;
    ExperianCreditReport hardInquiry;

    public Experian() {
    }

    public ExperianCreditReport getSoftInquiry(int ssn){
        // dummy values
        // TODO database check with 'creditReport'
        int score = 600;
        int creditLines = 10000;
        return new ExperianCreditReport(score, ssn, creditLines);    
    }

    public ExperianCreditReport getHardInquiry(int ssn){
        // dummy values
        // TODO database check with 'creditReport'
        int score = 600;
        int creditLines = 10000;
        return new ExperianCreditReport(score, ssn, creditLines);
    }
}
