package cardsystem.models;

import cardsystem.transaction.Transaction;

import java.util.List;

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
