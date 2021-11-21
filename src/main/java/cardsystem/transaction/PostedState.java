package cardsystem.transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PostedState {
    boolean isPosted();
    Optional<LocalDateTime> getPostedDate();
}
