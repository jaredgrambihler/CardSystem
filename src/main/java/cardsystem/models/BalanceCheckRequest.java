package cardsystem.models;

public class BalanceCheckRequest {
    private String authToken;
    private String accountId;

    public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
}
