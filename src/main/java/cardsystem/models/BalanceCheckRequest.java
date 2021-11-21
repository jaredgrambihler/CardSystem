package cardsystem.models;

import java.math.BigDecimal;

public class BalanceCheckRequest {
    private String authToken;
    private BigDecimal balance;

    public BigDecimal getBalance(){
        return balance;
    }

    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
}
