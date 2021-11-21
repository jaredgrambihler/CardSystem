package cardsystem.transaction;

import cardsystem.database.DatabaseSaver;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Transaction extends DatabaseSaver {

    double getAmount();

    String getTransactionId();

    String getAccountId();

    String getCounterparty();

    LocalDateTime getTransactionDate();

    boolean isPosted();

    Optional<LocalDateTime> getPostedDate();

    TransactionType getTransactionType();
}
