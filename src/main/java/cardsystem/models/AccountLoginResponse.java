package cardsystem.models;

public class AccountLoginResponse {
    private String AuthToken;

    public String getAuthToken(){
        return AuthToken;
    }

    public void setAuthToken(String AuthToken){
        this.AuthToken = AuthToken;
    }
}
