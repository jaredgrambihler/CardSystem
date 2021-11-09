package cardsystem.creditbureau;

import java.util.List;

public class CreditReportCreator {

    // Get Experian Credit Report
    public CreditReport createCreditReport(String ssn, int score, List<Integer> creditLines) {
        CreditReport creditReport = new ExperianCreditReport(ssn, score, creditLines);
        creditReport.saveToDatabase();
        return creditReport;
    }
}
