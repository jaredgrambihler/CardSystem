package cardsystem.transaction;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.time.LocalDateTime;
import java.util.*;

public class TransactionFetcher {

    public static Optional<Transaction> loadTransaction(String transactionId) {
        cardsystem.database.models.Transaction queryModel = new cardsystem.database.models.Transaction();
        queryModel.setTransactionId(transactionId);
        List<cardsystem.database.models.Transaction> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Transaction.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Transaction>()
                        .withHashKeyValues(queryModel)
        );
        if (!results.isEmpty()) {
            cardsystem.database.models.Transaction foundTransaction = results.get(0);
            Transaction loadedTransaction = loadTransactionFromDatabaseModel(foundTransaction);
            if (loadedTransaction != null) {
                return Optional.of(loadedTransaction);
            }
        }
        return Optional.empty();
    }

    public static List<Transaction> loadPostedTransactions(String accountId, LocalDateTime startTime, LocalDateTime endTime) {
        cardsystem.database.models.Transaction queryModel = new cardsystem.database.models.Transaction();
        queryModel.setAccountId(accountId);
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":accountId", new AttributeValue().withS(accountId));
        expressionAttributeValues.put(":startPostedDate", new AttributeValue().withS(DateConverter.getIso8601Timestamp(startTime)));
        expressionAttributeValues.put(":endPostedDate", new AttributeValue().withS(DateConverter.getIso8601Timestamp(endTime)));
        List<cardsystem.database.models.Transaction> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Transaction.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Transaction>()
                        .withKeyConditionExpression("accountId = :accountId and postedDate >= :startPostedDate and postedDate < :endPostedDate")
                        .withExpressionAttributeValues(expressionAttributeValues)
        );
        List<Transaction> transactions = new ArrayList<>();
        for (cardsystem.database.models.Transaction transactionModel : results) {
            Transaction transaction = loadTransactionFromDatabaseModel(transactionModel);
            if (transaction != null) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    private static Transaction loadTransactionFromDatabaseModel(cardsystem.database.models.Transaction transaction) {
        TransactionType transactionType = TransactionType.valueOf(transaction.getTransactionType());
        Transaction loadedTransaction;
        switch (transactionType) {
            case CASH_ADVANCE:
                loadedTransaction = loadCashAdvance(transaction);
                break;
            case MERCHANT:
                loadedTransaction = loadMerchantTransaction(transaction);
                break;
            case PAYMENT:
                loadedTransaction = loadPayment(transaction);
                break;
            case REFUND:
                loadedTransaction = loadRefund(transaction);
                break;
            default:
                return null;
        }
        return loadedTransaction;
    }

    public static CashAdvance loadCashAdvance(cardsystem.database.models.Transaction transaction) {
        return new CashAdvance(transaction.getTransactionId(), transaction.getAccountId(),
                transaction.getAmount().doubleValue(),
                DateConverter.getLocalDateTime(transaction.getTransactionDate()),
                getPostedDate(transaction)
        );
    }

    public static MerchantTransaction loadMerchantTransaction(cardsystem.database.models.Transaction transaction) {
        return new MerchantTransaction(transaction.getTransactionId(), transaction.getAccountId(),
                transaction.getAmount().doubleValue(),
                DateConverter.getLocalDateTime(transaction.getTransactionDate()),
                getPostedDate(transaction),
                transaction.getMerchant()
        );
    }

    public static Payment loadPayment(cardsystem.database.models.Transaction transaction) {
        return new Payment(transaction.getTransactionId(), transaction.getAccountId(),
                transaction.getAmount().doubleValue(),
                DateConverter.getLocalDateTime(transaction.getTransactionDate()),
                getPostedDate(transaction)
        );
    }

    public static Refund loadRefund(cardsystem.database.models.Transaction transaction) {
        return new Refund(transaction.getTransactionId(), transaction.getAccountId(),
                transaction.getAmount().doubleValue(),
                DateConverter.getLocalDateTime(transaction.getTransactionDate()),
                getPostedDate(transaction)
        );
    }

    private static Optional<LocalDateTime> getPostedDate(cardsystem.database.models.Transaction transaction) {
        Optional<LocalDateTime> postedDate = Optional.empty();
        if (transaction.getPostedDate() != null) {
            postedDate = Optional.of(DateConverter.getLocalDateTime(transaction.getPostedDate()));
        }
        return postedDate;
    }

}
