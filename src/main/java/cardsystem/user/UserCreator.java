package cardsystem.user;

import java.math.BigInteger;
import java.time.LocalDate;

public class UserCreator {
	
	public User createNewUser(String name, String ssn, String email, BigInteger income, LocalDate birthDate) {
		return new User(name, ssn, createUserId(), email, income, birthDate);
	}
	
	public String createUserId() {
		// TODO - implement unique id with database check
		return "xyz";
	}
	
	public User userLogin(String loginArg) {
		// TODO - implement user login
		return null;
	}
	

}
