package cardsystem.creditbureau;

public interface CreditBureau {
    public ExperianCreditReport getSoftInquiry();
    public ExperianCreditReport getHardInquiry();
}
