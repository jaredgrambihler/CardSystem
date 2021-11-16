package cardsystem.models;

public class UserApplicationRequest {
    private String authToken;
    private int age;
    private String ssn;
    private String validEmail;
    private int validSalary;
    private int creditScore;

    public String getAuthToken(){
        return authToken;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
    
    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSsn(){
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getValidEmail(){
        return validEmail;
    }

    public void setValidEmail(String validEmail) {
        this.validEmail = validEmail;
    }

    public int getValidSalary(){
        return validSalary;
    }

    public void setValidSalary(int validSalary){
        this.validSalary = validSalary;
    }

    public int getCreditScore(){
        return creditScore;
    }

    public void setCreditScore(int creditScore){
        this.creditScore = creditScore;
    }
}
