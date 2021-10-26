package cardsystem.account;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

public class UserAccount implements Account {
	private String name;
	private LocalDate birthDate;
	private String userId;
	private BigInteger income;
	
	/**
     * Create a user account.
     * @param name the name of the user
     * @param birthDate the birth date of the user
     * @param userId unique user id
     * @param income the income of the user 
     */
	public UserAccount(String name, LocalDate birthDate, String userId, BigInteger income) {
		this.name = name;
		this.birthDate = birthDate;
		this.userId = userId;
		this.income = income;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public int getAge() {
		Period age = Period.between(LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth()), LocalDate.now());
		return age.getYears();
	}

	public String getUserId() {
		return userId;
	}

	public BigInteger getIncome() {
		return income;
	}

	@Override
	public Collection<String> getAccountIds() {
		// TODO - implement getAccoundIds with database
		return null;
	}
}
