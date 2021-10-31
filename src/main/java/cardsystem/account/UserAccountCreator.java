package cardsystem.account;

import java.util.Optional;
import cardsystem.approval.UserApprover;

public class UserAccountCreator {
	
	public Optional<UserAccount> createNewUserAccount(String accountName, String accountNr, String userId, int salary, int age, String ssn, String validEmail) {
		if (UserApprover.isApproved(salary, age, ssn, validEmail)) {
			return Optional.of(new UserAccount(accountName, createAccountId(), accountNr, userId));
		}
		return Optional.empty();
	}
	
	public String createAccountId() {
		// TODO - implement unique id with database check
		return "def";
	}
	
	public void closeAccount(String accountId) {
		// TODO - implement account closure with database
	}
	
}


