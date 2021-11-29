package cardsystem.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;

public class DynamoDBCommunicator implements DatabaseCommunicator {

    @Override
    public <T> void save(T item) {
        DynamoDBConfig.getDefaultMapper().save(item);
    }

    @Override
    public <T> List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> query) {
        return DynamoDBConfig.getDefaultMapper().query(clazz, query.withConsistentRead(false));
    }
}
