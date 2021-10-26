package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    ExperianCreditReport softInquiry;
    ExperianCreditReport hardInquiry;

    public ExperianCreditReport getSoftInquiry(){
        // TODO database check
        return softInquiry;
    }

    public ExperianCreditReport getHardInquiry(){
        // TODO database check
        return hardInquiry;
    }
}
