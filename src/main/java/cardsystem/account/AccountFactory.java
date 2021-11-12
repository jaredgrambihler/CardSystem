package cardsystem.account;

import java.util.Optional;

public interface AccountFactory {
	public Optional<Account> createNewAccount(String accountName, String accountNr, String userId, int salary);
	public void closeAccount(String accountId);
}
