package cardsystem.approval;

public interface UserApprover {
    int income();
    int getAge();
    boolean identityGiven();
    boolean proofOfAddress();
}

class Approval implements UserApprover {
int salary;
int age;
    @Override
    public int income() {
        // TODO get Users Income
        return salary;
    }

    @Override
    public int getAge() {
        // TODO get users age, over 18
        return age;
    }

    @Override
    public boolean identityGiven() {
        // TODO have they been verified
        return false;
    }

    @Override
    public boolean proofOfAddress() {
        // TODO valid proof of address
        return false;
    }
    
}
