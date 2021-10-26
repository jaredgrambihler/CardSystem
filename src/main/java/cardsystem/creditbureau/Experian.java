package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    ExperianCreditReport softInquiry;
    ExperianCreditReport hardInquiry;

    public Experian (ExperianCreditReport softInquiry, ExperianCreditReport hardInquiry) {
        this.softInquiry = softInquiry
        this.hardInquiry = hardInquiry
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
