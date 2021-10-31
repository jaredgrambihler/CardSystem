package cardsystem.user;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;

public class User {
	private String name;
	private String ssn;
	private String userId;
	private String email;
	private BigInteger income;
	private LocalDate birthDate;
	
	/**
	 * Create a user.
	 * @param name the name of the user
	 * @param ssn the social security number of the user
	 * @param userId unique user id
	 * @param email the user's email address
	 * @param income the user's income
	 * @param birthDate the user's birth date
	 */
	public User(String name, String ssn, String userId, String email, BigInteger income, LocalDate birthDate) {
		this.name = name;
		this.ssn = ssn;
		this.userId = userId;
		this.email = email;
		this.income = income;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public String getSsn() {
		return ssn;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public BigInteger getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public int getAge() {
		Period age = Period.between(LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth()), LocalDate.now());
		return age.getYears();
	}
	
}
