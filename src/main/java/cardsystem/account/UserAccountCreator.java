package cardsystem.account;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

public interface UserAccountCreator {
	public Optional<UserAccount> createNewUserAccount(String name, LocalDate birthDate, BigInteger income);
	public String createUserId();
	public UserAccount userLogin();
	public void closeAccount(String accountId);
}


