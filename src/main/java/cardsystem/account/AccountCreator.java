package cardsystem.account;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import cardsystem.approval.UserApprover;
import cardsystem.database.DynamoDBCommunicator;
import cardsystem.email.*;
import cardsystem.user.User;
import cardsystem.user.UserFetcher;

public class AccountCreator implements AccountFactory {
	
	@Override
	public Optional<CreditCardAccount> createNewCreditCardAccount(String accountName, String userId, int salary) {
		if (UserApprover.isValidSalary(salary)) {
			CreditCardAccount creditCardAccount = new CreditCardAccount(accountName, createAccountId(), createNewAccountNumber(), userId);
			creditCardAccount.saveToDatabase();
			return Optional.of(creditCardAccount);
		}
		return Optional.empty();
	}
	
	private String createAccountId() {
		// create unique ID's until one is created without collision
        // collisions should rarely, if ever, occur
        while(true) {
            String accountId = UUID.randomUUID().toString();
            if (AccountFetcher.loadCreditCardAccount(accountId).isEmpty()) {
                return accountId;
            }
        }
	}
	
	// Close account by appending " - CLOSED" to account name + notifying user by email
	@Override
	public void closeAccount(String accountId) {
		// TODO - check if balance is 0 before closing
		
		Optional<cardsystem.database.models.Account> databaseAccount = AccountFetcher.loadAccountDatabaseModel(accountId);
		Optional<CreditCardAccount> creditCardAccount = AccountFetcher.loadCreditCardAccount(accountId);
		if (databaseAccount.isPresent()) {
			cardsystem.database.models.Account foundAccount = databaseAccount.get();
			String closedAccountName = foundAccount.getAccountName() + " - CLOSED";
			foundAccount.setAccountName(closedAccountName);
			new DynamoDBCommunicator().save(foundAccount);
		}
		if (creditCardAccount.isPresent()) {
			CreditCardAccount foundAccount = creditCardAccount.get();
			sendAccountClosureEmail(foundAccount);
			String closedAccountName = foundAccount.getAccountName() + " - CLOSED";
			foundAccount.setAccountName(closedAccountName);
		}
	}

	private void sendAccountClosureEmail(Account account) {
		Optional<User> user = UserFetcher.loadUser(account.getUserId());
		if (user.isPresent()) {
			String emailAddress = user.get().getEmailAddress();
			if (emailAddress != null) {
				Email email = new EmailFactory().getAccountEmail(account, emailAddress, "Account Closure", 
						"You have successfully closed the account '" + account.getAccountNumber() + "' with the name '" 
								+ account.getAccountName() + "'.");
				new DefaultEmailSenderFactory().getEmailSender().send(email);
			}
		}
	}
	
	public String createNewAccountNumber() {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 15; i++) {
			builder.append(random.nextInt(10));
		}
		return builder.toString();
	}
	
}


