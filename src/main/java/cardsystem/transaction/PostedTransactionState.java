package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class PostedTransactionState implements PostedState {

    private LocalDateTime postedDate;

    public PostedTransactionState(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public boolean isPosted() {
        return true;
    }

    @Override
    public Optional<LocalDateTime> getPostedDate() {
        return Optional.of(postedDate);
    }
}
