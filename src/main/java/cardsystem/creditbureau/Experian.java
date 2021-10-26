package cardsystem.creditbureau;

public class Experian implements CreditBureau {
    public ExperianCreditReport getSoftInquiry(){
        // TODO database check
        return ExperianCreditReport;
    }

    public ExperianCreditReport getHardInquiry(){
        // TODO database check
        return ExperianCreditReport;
    }
}
