package cardsystem.models;

public class CheckCreditLineRequest {
    private String authToken;
    private int creditLines;

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public int getCreditLines(){
        return creditLines;
    }

    public void setCreditLines(int creditLines){
        this.creditLines = creditLines;
    }
}
