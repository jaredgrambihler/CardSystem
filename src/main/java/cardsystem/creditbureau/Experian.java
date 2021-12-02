package cardsystem.creditbureau;

import java.util.Arrays;
import java.util.List;

public class Experian implements CreditBureau {
    public Experian() {
    }

    public ExperianCreditReport getSoftInquiry(String ssn){
        // dummy values
        // TODO database check with 'creditReport'
        int score = 600;
        List<Integer> creditLines = Arrays.asList(10000);
        return new ExperianCreditReport(ssn, score, creditLines);    
    }

    public ExperianCreditReport getHardInquiry(String ssn){
        // dummy values
        // TODO database check with 'creditReport'
        int score = 600;
        List<Integer> creditLines = Arrays.asList(10000);
        return new ExperianCreditReport(ssn, score, creditLines);
    }
}
