package cardsystem.creditbureau;

public interface CreditReport {
    public int getScore(String ssn);
    public int getTotalCreditLines();
}
