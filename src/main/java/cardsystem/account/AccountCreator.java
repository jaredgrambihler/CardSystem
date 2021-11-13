package cardsystem.account;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import cardsystem.approval.CreditLimit;
import cardsystem.approval.UserApprover;
import cardsystem.creditbureau.Experian;
import cardsystem.creditbureau.ExperianCreditReport;
import cardsystem.database.DynamoDBCommunicator;
import cardsystem.user.User;
import cardsystem.user.UserFetcher;

public class AccountCreator implements AccountFactory {
	
	@Override
	public Optional<CreditCardAccount> createNewCreditCardAccount(String accountName, String userId, int salary) {
		if (UserApprover.isValidSalary(salary)) {
			CreditCardAccount creditCardAccount = new CreditCardAccount(accountName, createAccountId(), createNewAccountNumber(), userId);
			creditCardAccount.setCreditLimit(getUserCreditLimit(userId, salary));
			creditCardAccount.saveToDatabase();
			return Optional.of(creditCardAccount);
		}
		return Optional.empty();
	}
	
	private int getUserCreditLimit(String userId, int salary) {
		Experian creditBureau = new Experian();
		Optional<User> user = UserFetcher.loadUser(userId);
		
		int userCreditLimit = 0;
		
		if (user.isPresent()) {
			User accountOwner = user.get();
			String ssn = accountOwner.getSsn();
			ExperianCreditReport experian = creditBureau.getHardInquiry(ssn);
			
			CreditLimit creditLimit = new CreditLimit();
			
			// Unsure if this is the correct way to calculate totalCurrentCredit?
			int totalCurrentCredit = 0;
			for (int credit : experian.getCreditLines()) {
				totalCurrentCredit += credit;
			}
			userCreditLimit = creditLimit.determineCreditLimit(salary, experian.getScore(), totalCurrentCredit);
		}
		return userCreditLimit;
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
	
	// Close account by appending " - CLOSED" to account name 
	@Override
	public void closeAccount(String accountId) {
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
			String closedAccountName = foundAccount.getAccountName() + " - CLOSED";
			foundAccount.setAccountName(closedAccountName);
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


