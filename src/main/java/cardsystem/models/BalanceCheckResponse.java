package cardsystem.models;

import java.math.BigDecimal;

public class BalanceCheckResponse {

    private BigDecimal balance;
    private BigDecimal availableCredit;
    private int creditLimit;
    

    public BigDecimal getAvailableCredit() {
		return availableCredit;
	}

	public void setAvailableCredit(BigDecimal availableCredit) {
		this.availableCredit = availableCredit;
	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getBalance(){
        return balance;
    }

    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
    
}
