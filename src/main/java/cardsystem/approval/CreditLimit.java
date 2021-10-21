package cardsystem.approval;

public interface CreditLimit {
    boolean goodCreditHistory();
    int CreditScore();
}

class Limit implements CreditLimit {
int Score;

    @Override
    public int CreditScore() {
        // TODO Auto-generated method stub
        return Score;
    }

    @Override
    public boolean goodCreditHistory() {
       if (Score < 600) {
           return false;
       } else {
        return false;
       }
    }

}
