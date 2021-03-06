package cardsystem.account;

import cardsystem.balance.TransactionHandler;
import cardsystem.database.DynamoDBCommunicator;

import java.math.BigDecimal;

public class CreditCardAccount implements Account {
	private String accountName;
	private String accountId;
	private String accountNumber;
	private String userId;
	private int creditLimit;

	/**
	 * Create a credit card account
	 * @param accountName the name of the account
	 * @param accountId the unique id of the account
	 * @param accountNumber the account number
	 * @param userId the id of the user who owns the account
	 * @param creditLimit the credit limit of the account
	 */
	public CreditCardAccount(String accountName, String accountId, String accountNumber, String userId, int creditLimit) {
		this.accountName = accountName;
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.userId = userId;
		this.creditLimit = creditLimit;
	}

	@Override
	public String getAccountName() {
		return accountName;
	}

	@Override
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String getAccountId() {
		return accountId;
	}

	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String getUserId() {
		return userId;
	}
	
	public int getCreditLimit() {
		return this.creditLimit;
	}
	
	@Override
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
		new TransactionHandler().onCreditLimitChange(this, BigDecimal.valueOf(creditLimit));
	}
	
	public void saveToDatabase() {
        new DynamoDBCommunicator().save(createDatabaseModel());
	}

	/**
	 * Create the database model.
	 * @return database model object with fields populated
	 */
	protected cardsystem.database.models.Account createDatabaseModel() {
	    cardsystem.database.models.Account account = new cardsystem.database.models.Account();
	    account.setAccountName(getAccountName());
	    account.setAccountId(getAccountId());
	    account.setAccountNumber(getAccountNumber());
	    account.setUserId(getUserId());
	    account.setCreditLimit(getCreditLimit());
	    return account;
	}

}
