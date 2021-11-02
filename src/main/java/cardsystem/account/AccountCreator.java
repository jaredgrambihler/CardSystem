package cardsystem.account;

import java.util.Optional;
import cardsystem.approval.UserApprover;

public class AccountCreator {
	
	public Optional<CreditCardAccount> createNewCreditCardAccount(String accountName, String accountNr, String userId, int salary) {
		if (UserApprover.isValidSalary(salary)) {
			return Optional.of(new CreditCardAccount(accountName, createAccountId(), accountNr, userId));
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


