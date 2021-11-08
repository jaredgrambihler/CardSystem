package cardsystem.statement;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;
import cardsystem.transaction.TransactionFetcher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.time.LocalDateTime;
import java.util.*;

public class CreditCardStatementFetcher implements StatementFetcher {

    public Optional<Statement> getLatestStatement(String accountId) {
        cardsystem.database.models.Statement statement = new cardsystem.database.models.Statement();
        statement.setAccountId(accountId);
        List<cardsystem.database.models.Statement> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Statement.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Statement>()
                        .withHashKeyValues(statement)
                        .withScanIndexForward(true)
                        .withLimit(1)
        );
        if (results.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(loadCreditCardStatement(statement));
        }
    }

    // get first statement before the specified time
    public Optional<Statement> getStatement(String accountId, LocalDateTime localDateTime) {
        cardsystem.database.models.Statement statement = new cardsystem.database.models.Statement();
        statement.setAccountId(accountId);
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":accountId", new AttributeValue().withS(accountId));
        expressionAttributeValues.put(":queryDate", new AttributeValue().withS(DateConverter.getIso8601Timestamp(localDateTime)));
        List<cardsystem.database.models.Statement> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Statement.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Statement>()
                        .withHashKeyValues(statement)
                        .withKeyConditionExpression("accountId = :accountId and endDate <= :queryDate")
                        .withExpressionAttributeValues(expressionAttributeValues)
                        .withLimit(1)
        );
        if (results.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(loadCreditCardStatement(results.get(0)));
        }
    }

    private CreditCardStatement loadCreditCardStatement(cardsystem.database.models.Statement statement) {
        LocalDateTime startTime = DateConverter.getLocalDateTime(statement.getStartDate());
        LocalDateTime endTime = DateConverter.getLocalDateTime(statement.getEndDate());
        return new CreditCardStatement(statement.getAccountId(),
                statement.getBalance().doubleValue(),
                TransactionFetcher.loadPostedTransactions(statement.getAccountId(), startTime, endTime),
                new StatementPeriod(
                        startTime.toLocalDate(),
                        endTime.toLocalDate()
                ),
                statement.getRewards()
        );
    }
}
