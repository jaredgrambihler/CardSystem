package cardsystem.models;

public class CheckCreditLineRequest {
    private String authToken;
    private String ssn;

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public String getSsn(){
        return ssn;
    }

    public void setSsn(String ssn){
        this.ssn = ssn;
    }
}
