package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    ExperianCreditReport softInquiry;
    ExperianCreditReport hardInquiry;

    public Experian() {
    }

    public ExperianCreditReport getSoftInquiry(){
        CreditReport creditReport = new ExperianCreditReport
        // TODO database check with 'creditReport'
        return softInquiry;
    }

    public ExperianCreditReport getHardInquiry(){
        CreditReport creditReport = new ExperianCreditReport
        // TODO database check with 'creditReport'
        return hardInquiry;
    }
}
