package cardsystem.statement;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StatementFetcher {
     Optional<Statement> getLatestStatement(String accountId);
     Optional<Statement> getStatement(String accountId, LocalDateTime localDateTime);
}
