package cardsystem.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListTransactionsRequest {
    private String authToken;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public void setEnd(LocalDateTime endTime){
        this.endTime = endTime;
    }
}
