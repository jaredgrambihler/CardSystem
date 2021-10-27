package cardsystem.statement;

import java.time.LocalDate;

public class StatementPeriod {

    private LocalDate startDate;
    private LocalDate endDate;

    public StatementPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
