package cardsystem.creditbureau;

import cardsystem.database.DynamoDBCommunicator;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Optional;

public class CreditReportFetcher {
    public static Optional<CreditReport> loadCreditReport(String ssn) {
        cardsystem.database.models.CreditBureau queryModel = new cardsystem.database.models.CreditBureau();
        queryModel.setSsn(ssn);
        List<cardsystem.database.models.CreditBureau> results = new DynamoDBCommunicator().query(
            cardsystem.database.models.CreditBureau.class,
            new DynamoDBQueryExpression<cardsystem.database.models.CreditBureau>()
                    .withHashKeyValues(queryModel)
        );

        if(!results.isEmpty()){
            cardsystem.database.models.CreditBureau  foundCreditReport = results.get(0);
            CreditReport loadedCreditReport = loadCreditReportFromDatabaseModel(foundCreditReport);
            return Optional.of(loadedCreditReport);
        }
        return Optional.empty();
    }

    public static CreditReport loadCreditReportFromDatabaseModel (cardsystem.database.models.CreditBureau creditReport) {
        return new ExperianCreditReport (creditReport.getSsn(), creditReport.getScore(), creditReport.getCreditLines());
    }
}
