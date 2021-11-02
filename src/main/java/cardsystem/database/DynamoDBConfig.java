package cardsystem.database;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoDBConfig {

    private static final DynamoDBMapper mapper = createMapper();

    public static DynamoDBMapper getDefaultMapper() {
        return mapper;
    }

    private static DynamoDBMapper createMapper() {
        AmazonDynamoDB client = getClient();
        DynamoDBMapperConfig mapperConfig = getMapperConfigBuilder().build();
        return new DynamoDBMapper(client, mapperConfig);
    }

    private static DynamoDBMapperConfig.Builder getMapperConfigBuilder() {
        return DynamoDBMapperConfig
                .builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withTableNameResolver(new DynamoDBMapperConfig.DefaultTableNameResolver())
                .withTableNameOverride(null)
                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING);
    }

    private static AmazonDynamoDB getClient() {
         return AmazonDynamoDBClientBuilder.standard()
                 .withRegion(Regions.EU_WEST_1)
                 .build();
    }
}
