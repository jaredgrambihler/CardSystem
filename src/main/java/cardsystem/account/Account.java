package cardsystem.account;

public interface Account {
	
	public String getAccountName();
	public void setAccountName(String accountName);
	public String getAccountId();
	public String getAccountNumber();
	public String getUserId();
	public void setCreditLimit(int creditLimit);
	
}
