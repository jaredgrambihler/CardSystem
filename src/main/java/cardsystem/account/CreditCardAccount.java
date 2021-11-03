package cardsystem.account;

public class CreditCardAccount implements Account {
	private String accountName;
	private String accountId;
	private String accountNr;
	private String userId; 
	
	/**
	 * Create a credit card account
	 * @param accountName the name of the account
	 * @param accountId the unique id of the account
	 * @param accountNr the account number
	 * @param userId the id of the user who owns the account
	 */
	public CreditCardAccount(String accountName, String accountId, String accountNr, String userId) {
		this.accountName = accountName;
		this.accountId = accountId;
		this.accountNr = accountNr;
		this.userId = userId;
	}

	@Override
	public String getAccountName() {
		return accountName;
	}

	@Override
	public String getAccountId() {
		return accountId;
	}

	@Override
	public String getAccountNr() {
		return accountNr;
	}

	@Override
	public String getUserId() {
		return userId;
	}


}
