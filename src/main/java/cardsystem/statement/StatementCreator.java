package cardsystem.statement;

public interface StatementCreator {

    public Statement createStatement(String accountId, StatementPeriod statementPeriod);

}
