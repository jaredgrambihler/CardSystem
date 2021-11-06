package cardsystem.account;

import java.util.Optional;
import java.util.UUID;

import cardsystem.approval.UserApprover;

public class AccountCreator {
	
	public Optional<CreditCardAccount> createNewCreditCardAccount(String accountName, String accountNr, String userId, int salary) {
		if (UserApprover.isValidSalary(salary)) {
			CreditCardAccount creditCardAccount = new CreditCardAccount(accountName, createAccountId(), accountNr, userId);
			creditCardAccount.saveToDatabase();
			return Optional.of(creditCardAccount);
		}
		return Optional.empty();
	}
	
	public String createAccountId() {
		// create unique ID's until one is created without collision
        // collisions should rarely, if ever, occur
        while(true) {
            String accountId = UUID.randomUUID().toString();
            if (AccountFetcher.loadCreditCardAccount(accountId).isEmpty()) {
                return accountId;
            }
        }
	}
	
	public void closeAccount(String accountId) {
		// TODO - implement account closure with database
	}
	
}


