package cardsystem.models;

public class CheckCreditLine {
    private String accountId;
    private int creditLines;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public int getCreditLines(){
        return creditLines;
    }

    public void setCreditLines(int creditLines){
        this.creditLines = creditLines;
    }
}
