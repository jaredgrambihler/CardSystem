package cardsystem.models;

public class EmailNotificationsRequest {
    private String authToken;

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

}
