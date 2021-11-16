package cardsystem.models;

import java.util.List;
import cardsystem.transaction.Transaction;

public class ListTransactionsResponse {
    //Array of transactions (pending, posted)
    private List<Transaction> transactions;

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
    }
}
