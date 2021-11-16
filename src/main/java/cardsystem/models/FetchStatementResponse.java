package cardsystem.models;

import java.time.LocalDateTime;

public class FetchStatementResponse {
    private String accountId;
    private LocalDateTime localDateTime;

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public LocalDateTime getLocalDateTime(){
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime){
        this.localDateTime = localDateTime;
    }
}
