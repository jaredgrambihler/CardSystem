package cardsystem.approval;

public class UserApprover {
    
    public boolean isApproved(int salary, int age, String SSN, String validEmail){
        if (isValidAge(age) && isValidSalary(salary) && isValidSSN(SSN) && isValidEmail(validEmail)) {
        }
        return true;
    }

    private boolean isValidAge(int age) {
        return age >= 18;
    }

    private boolean isValidSalary(int salary) {
        return salary >= 20000;
    }

    private boolean isValidSSN(String SSN) {
        return SSN.length() == 9;
    }

    private boolean isValidEmail(String validEmail){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(validEmail);
        return m.matches();
    }

    //This might not work, may need to look at this after
}
 