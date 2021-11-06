package cardsystem.user;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import cardsystem.database.DateConverter;
import cardsystem.database.DynamoDBCommunicator;

public class User implements UserInterface {
	private String name;
	private String ssn;
	private String userId;
	private String emailAddress;
	private BigInteger income;
	private LocalDate birthDate;
	
	/**
	 * Create a user.
	 * @param name the name of the user
	 * @param ssn the social security number of the user
	 * @param userId unique user id
	 * @param emailAddress the user's email address
	 * @param income the user's income
	 * @param birthDate the user's birth date
	 */
	public User(String name, String ssn, String userId, String emailAddress, BigInteger income, LocalDate birthDate) {
		this.name = name;
		this.ssn = ssn;
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.income = income;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public String getSsn() {
		return ssn;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public BigInteger getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public int getAge() {
		Period age = Period.between(LocalDate.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth()), LocalDate.now());
		return age.getYears();
	}
	
	@Override
	public Collection<String> getAccountIds() {
		cardsystem.database.models.Account queryModel = new cardsystem.database.models.Account();
        queryModel.setUserId(userId);
		List<cardsystem.database.models.Account> results = new DynamoDBCommunicator().query(
                cardsystem.database.models.Account.class,
                new DynamoDBQueryExpression<cardsystem.database.models.Account>()
                        .withHashKeyValues(queryModel)
        );
        List<String> accountIds = new ArrayList<>();
        for (cardsystem.database.models.Account accountModel : results) {
            if (accountModel != null) {
                accountIds.add(accountModel.getAccountId());
            }
        }
        return accountIds;
	}
	
	public void saveToDatabase() {
		new DynamoDBCommunicator().save(createDatabaseModel());
	}

    /**
     * Create the database model.
     * @return database model object with fields populated
     */
    protected cardsystem.database.models.User createDatabaseModel() {
        cardsystem.database.models.User user = new cardsystem.database.models.User();
        user.setUserId(getUserId());
        user.setName(getName());
        user.setBirthDate(DateConverter.getIso8601Date(getBirthDate()));
        user.setIncome(getIncome());
        user.setEmailAddress(getEmailAddress());
        user.setSsn(getSsn());
        return user;
    }
}
