package cardsystem.models;

import cardsystem.transaction.Transaction;

import java.util.List;

public class FetchStatementResponse {
    private String accountId;
    private double balance;
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }
    
    
}
