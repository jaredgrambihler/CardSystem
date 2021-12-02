package cardsystem.balance;

import cardsystem.account.CreditCardAccount;
import cardsystem.transaction.Transaction;

import java.math.BigDecimal;

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
                BigDecimal currentBalance = balance.getBalance().add(BigDecimal.valueOf(transaction.getAmount()));
                balance.updateBalance(currentBalance);
            } 
        }
        balance.saveToDatabase();
    } 

    public void onCreditLimitChange(CreditCardAccount account, BigDecimal amount) {
        Balance balance = BalanceFetcher.getLatestBalance(account.getAccountId());
        BigDecimal newAvailableCredit = balance.getAvailableCredit().add(amount);
        balance.updateAvailableCredit(newAvailableCredit);
        balance.saveToDatabase();
        
    }
}
