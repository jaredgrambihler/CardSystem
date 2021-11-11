package cardsystem.balance;

import java.math.BigDecimal;

public interface Balance {
    public BigDecimal checkBalance();
    public Double getTransaction();
    public BigDecimal subtractTransaction(BigDecimal balance, Double transaction);
}
