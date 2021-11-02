package cardsystem.approval;

public class UserApprover {
    
    public static boolean isApproved(int age, String SSN, String validEmail){
        if (isValidAge(age) && isValidSSN(SSN) && isValidEmail(validEmail)) {
        return true;
        }
        return false;
    }

    private static boolean isValidAge(int age) {
        return age >= 18;
    }

    public static boolean isValidSalary(int salary) {
        return salary >= 20000;
    }

    private static boolean isValidSSN(String SSN) {
        return SSN.length() == 9;
    }

    private static boolean isValidEmail(String validEmail){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(validEmail);
        return m.matches();
    }

    //This might not work, may need to look at this after
}
 