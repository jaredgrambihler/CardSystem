package cardsystem.approval;

public class CreditLimit {

 public int determineCreditLimit(int income, int creditScore, int totalCurrentCredit){
    if (creditScore < 700) {
        int newCreditLimit = (int) (income*0.15);
        Math.round(newCreditLimit);
        return newCreditLimit;
    } else {
            int newCreditLimit = (int) (income*0.25);
            Math.round(newCreditLimit);
            return newCreditLimit;
        }

    }
}