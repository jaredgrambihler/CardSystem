package cardsystem.models;

import java.math.BigInteger;

public class UserApplicationRequest {
    private String ssn;
    private String email;
    private String name;
    private String birthDate;
    private BigInteger income;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public BigInteger getIncome() {
		return income;
	}

	public void setIncome(BigInteger income) {
		this.income = income;
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
