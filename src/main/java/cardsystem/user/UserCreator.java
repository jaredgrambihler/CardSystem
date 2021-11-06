package cardsystem.user;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;

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
		// create unique ID's until one is created without collision
        // collisions should rarely, if ever, occur
        while(true) {
            String userId = UUID.randomUUID().toString();
            if (UserFetcher.loadUser(userId).isEmpty()) {
                return userId;
            }
        }
	}
	
}
