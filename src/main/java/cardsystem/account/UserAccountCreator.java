package cardsystem.account;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;
import cardsystem.approval.UserApprover;

public class UserAccountCreator {
	
	public Optional<UserAccount> createNewUserAccount(String name, LocalDate birthDate, BigInteger income, int salary, int age, String SSN, String validEmail) {
		if (UserApprover.isApproved(salary, age, SSN, validEmail)) {
			return Optional.of(new UserAccount(name, birthDate, createUserId(), income));
		}
		return Optional.empty();
	}
	
	public String createUserId() {
		// TODO - implement unique id with database check
		return "xyz";
	}
	
	public UserAccount userLogin(String loginArg) {
		// TODO - implement user login
		return null;
	}
	
	public void closeAccount(String accountId) {
		// TODO - implement account closure with database
	}
	
}


