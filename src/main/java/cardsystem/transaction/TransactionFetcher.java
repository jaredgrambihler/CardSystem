package cardsystem.transaction;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TransactionFetcher {

    public static Optional<Transaction> loadTransaction(String transactionId) {
        cardsystem.database.models.Transaction queryModel = new cardsystem.database.models.Transaction();
        queryModel.setTransactionId(transactionId);
        List<cardsystem.database.models.Transaction> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Transaction.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Transaction>()
                        .withHashKeyValues(queryModel)
        );
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            cardsystem.database.models.Transaction foundTransaction = results.get(0);
            TransactionType transactionType = TransactionType.valueOf(foundTransaction.getTransactionType());
            Transaction transaction;
            switch (transactionType) {
                case CASH_ADVANCE:
                    transaction = loadCashAdvance(foundTransaction);
                    break;
                case MERCHANT:
                    transaction = loadMerchantTransaction(foundTransaction);
                    break;
                case PAYMENT:
                    transaction = loadPayment(foundTransaction);
                    break;
                case REFUND:
                    transaction = loadRefund(foundTransaction);
                    break;
                default:
                    return Optional.empty();
            }
            return Optional.of(transaction);
        }
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
