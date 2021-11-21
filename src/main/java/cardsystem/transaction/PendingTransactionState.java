package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class PendingTransactionState implements PostedState {

    public PendingTransactionState() {

    }

    @Override
    public boolean isPosted() {
        return false;
    }

    @Override
    public Optional<LocalDateTime> getPostedDate() {
        return Optional.empty();
    }
}
