package cardsystem.user;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import cardsystem.approval.UserApprover;

public class UserCreator {
	
	public Optional<User> createNewUser(String name, String ssn, String email, BigInteger income, LocalDate birthDate) {
		int age = (Period.between(LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth()), LocalDate.now())).getYears();
		if (UserApprover.isApproved(age, ssn, email)) { 
			return Optional.of(new User(name, ssn, createUserId(), email, income, birthDate));
		}
		return Optional.empty();
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
