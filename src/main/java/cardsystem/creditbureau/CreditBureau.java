package cardsystem.creditbureau;

public interface CreditBureau {
    public CreditReport getSoftInquiry(String ssn);
    public CreditReport getHardInquiry(String ssn);
}
