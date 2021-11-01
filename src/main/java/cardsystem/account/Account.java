package cardsystem.account;

public class Account {
	private String accountName;
	private String accountId;
	private String accountNr;
	private String userId; 
	
	/**
	 * Create an account
	 * @param accountName the name of the account
	 * @param accountId the unique id of the account
	 * @param accountNr the account number
	 * @param userId the id of the user who owns the account
	 */
	public Account(String accountName, String accountId, String accountNr, String userId) {
		this.accountName = accountName;
		this.accountId = accountId;
		this.accountNr = accountNr;
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAccountNr() {
		return accountNr;
	}

	public String getUserId() {
		return userId;
	}
}
