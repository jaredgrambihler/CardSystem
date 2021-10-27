package cardsystem.statement;

import java.time.LocalDate;

public interface StatementCreator {

    public Statement createStatement(String accountId, StatementPeriod statementPeriod);

}
