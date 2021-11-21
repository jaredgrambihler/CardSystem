package cardsystem.models;

public class UserApplicationRequest {
    private int age;
    private String ssn;
    private String email;
    
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
