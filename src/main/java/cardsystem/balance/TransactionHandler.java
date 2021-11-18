package cardsystem.balance;

import java.math.BigDecimal;
import cardsystem.transaction.Transaction;
import cardsystem.account.CreditCardAccount;

public class TransactionHandler {
    public void onTransaction(Transaction transaction) {
        Balance balance = BalanceFetcher.getLatestBalance(transaction.getAccountId());
        balance.updateAvailableCredit(balance.getAvailableCredit().subtract(BigDecimal.valueOf(transaction.getAmount())));
        balance.saveToDatabase();
    }

    public void onTransactionPosted(Transaction transaction) {
        Balance balance = BalanceFetcher.getLatestBalance(transaction.getAccountId());
        if (transaction.getPostedDate().isPresent()) {
            if (transaction.getAccountId() == balance.getAccountId()) {
                BigDecimal currentBalance = balance.getBalance().subtract(BigDecimal.valueOf(transaction.getAmount()));
                balance.updateBalance(currentBalance);
            } 
        }
        balance.saveToDatabase();
    } 

    public void onCreditLimitChange(CreditCardAccount account, BigDecimal amount) {
        Balance balance = BalanceFetcher.getLatestBalance(account.getAccountId());
        if(amount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal newCreditLimit = BigDecimal.valueOf(account.getCreditLimit()).add(amount);
            account.setCreditLimit(newCreditLimit.intValue());
            balance.saveToDatabase();
        }
        else {
            BigDecimal newCreditLimit = BigDecimal.valueOf(account.getCreditLimit()).subtract(amount);
            account.setCreditLimit(newCreditLimit.intValue());
            balance.saveToDatabase();
        }
        
    }
}
