package cardsystem.account;

import java.util.Optional;

public interface AccountFactory {
	public Optional<CreditCardAccount> createNewCreditCardAccount(String accountName, String userId, int salary);
	public boolean closeAccount(String accountId);
}
