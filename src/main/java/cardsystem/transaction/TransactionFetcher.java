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
                        .withKeyConditionExpression("accountId = :accountId and postedDate BETWEEN :startPostedDate and :endPostedDate")
                        .withExpressionAttributeValues(expressionAttributeValues)
                        .withIndexName("accountIndex")
        );
        List<Transaction> transactions = new ArrayList<>();
        for (cardsystem.database.models.Transaction transactionModel : results) {
            // filter out transactions that are beyond the target date
            if (transactionModel.getPostedDate().compareTo(DateConverter.getIso8601Timestamp(endTime)) > 0) {
                continue;
            }
            Transaction transaction = loadTransactionFromDatabaseModel(transactionModel);
            if (transaction != null) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    private static Transaction loadTransactionFromDatabaseModel(cardsystem.database.models.Transaction transaction) {
        TransactionType transactionType = TransactionType.valueOf(transaction.getTransactionType());
        return new TransactionImpl(transaction.getTransactionId(),
                transaction.getAccountId(),
                transaction.getAmount().doubleValue(),
                transaction.getCounterparty(),
                DateConverter.getLocalDateTime(transaction.getTransactionDate()),
                transactionType,
                getPostedDate(transaction)
        );
    }

    private static LocalDateTime getPostedDate(cardsystem.database.models.Transaction transaction) {
        if (transaction.getPostedDate() != null) {
            return DateConverter.getLocalDateTime(transaction.getPostedDate());
        } else {
            return null;
        }
    }

}
