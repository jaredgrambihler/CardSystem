package cardsystem.approval;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UserApproverTest {

    private int age;
    private String ssn;
    private String email;
    private boolean expectedApproval;

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { 17, "123456789", "jim@gmail.com", false },
                { 18, "12345678", "jim@gmail.com", false },
                { 25, "123456789", "not_an_email", false },
                { 18, "123456789", "jim@gmail.com", true },
                { 40, "123456789", "custom@mydomain.com", true }
        });
    }

    public UserApproverTest(int age, String ssn, String email, boolean expectedApproval) {
        this.age = age;
        this.ssn = ssn;
        this.email = email;
        this.expectedApproval = expectedApproval;
    }

    @Test
    public void testIsApproved() {
        boolean actual = UserApprover.isApproved(age,ssn, email);
        assertEquals(expectedApproval, actual);
    }
}
