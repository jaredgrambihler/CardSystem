package cardsystem.account;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

public class UserAccountCreator {
	public Optional<UserAccount> createNewUserAccount(String name, LocalDate birthDate, BigInteger income) {
		return Optional.of(new UserAccount(name, birthDate, createUserId(), income));
	}
	
	public String createUserId() {
		// TODO - implement unique id with database check
		return "xyz";
	}
	
	public UserAccount userLogin() {
		// TODO - implement user login
		return null;
	}
	
	public void closeAccount(String accountId) {
		// TODO - implement account closure with database
	}
}


