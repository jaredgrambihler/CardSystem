package cardsystem.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;

public interface DatabaseCommunicator {

    public <T> void save(T item);
    public <T> List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> query);

}
