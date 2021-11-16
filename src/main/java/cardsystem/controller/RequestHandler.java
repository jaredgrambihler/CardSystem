package cardsystem.controller;

import cardsystem.account.AccountCreator;
import cardsystem.account.AccountFetcher;
import cardsystem.account.CreditCardAccount;
import cardsystem.auth.Token;
import cardsystem.auth.TokenFactory;
import cardsystem.creditbureau.CreditReport;
import cardsystem.creditbureau.CreditReportFetcher;
import cardsystem.models.*;
import cardsystem.rewards.RewardFetcher;
import cardsystem.rewards.RewardRedeemer;
import cardsystem.statement.CreditCardStatementFetcher;
import cardsystem.statement.Statement;
import cardsystem.transaction.Transaction;
import cardsystem.transaction.TransactionCreator;
import cardsystem.transaction.TransactionType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.Optional;

public class RequestHandler {

    private static final Gson gson = new GsonBuilder().create();

    public static Object handleRequest(String action, String requestBody) {
        Object jsonObject = new JsonObject();
        switch (action) {
            case "BalancePayment":
                jsonObject = makeTransaction(requestBody, TransactionType.PAYMENT);
                break;
            case "MerchantTransaction":
                jsonObject = makeTransaction(requestBody, TransactionType.MERCHANT);
                break;
            case "CashAdvanceTransaction":
                jsonObject = makeTransaction(requestBody, TransactionType.CASH_ADVANCE);
                break;
            case "CheckBalance":
                jsonObject = getBalanceCheck(requestBody);
                break;
            case "CheckCreditLine":
                jsonObject = getCheckCreditLine(requestBody);
                break;
            case "CreditLimitCheck":
                jsonObject = getCreditLimitCheck(requestBody);
                break;
            case "ListTransactions":
                jsonObject = getListTransactions(requestBody);
                break;
            case "FetchStatementPeriod":
                jsonObject = getFetchStatementPeriod(requestBody);
                break;
            case "EmailNotifications":
                jsonObject = getEmailNotifications(requestBody);
                break;
            case "AccountLogin":
                jsonObject = getAccountLogin(requestBody);
                break;
            case "AccountClosure":
                jsonObject = getAccountClosure(requestBody);
                break;
            case "CreateStatement":
                jsonObject = getCreateStatement(requestBody);
                break;
            case "AccountApply":
                jsonObject = getUserApplication(requestBody);
                break;
            case "AccountCreation":
                jsonObject = getAccountCreation(requestBody);
                break;
            case "RedeemRewards":
            	jsonObject = getRedeemRewards(requestBody);
                break;
            default:
                // Unknown action
                JsonObject response = new JsonObject();
                response.addProperty("action", action);
                response.addProperty("body", requestBody);
                response.addProperty("message", "unknown action");
                jsonObject = response;
                break;
        }
        return jsonObject;
    }

    private static TransactionResponse makeTransaction(String requestBody, TransactionType transactionType) {
        TransactionRequest transactionRequest = gson.fromJson(requestBody, TransactionRequest.class);
        Token token = TokenFactory.createToken(transactionRequest.getAuthToken());
        String accountId = transactionRequest.getAccountId();
        String counterparty = transactionRequest.getCounterparty();
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setApproved(false);

        // check arguments are valid for creating a transaction, else return
        if (accountId == null || counterparty == null) {
            return transactionResponse;
        }

        if (token.getAccountIds().contains(accountId)) {
            Optional<Transaction> transaction = new TransactionCreator().createTransaction(
                    accountId,
                    transactionRequest.getAmount(),
                    counterparty,
                    transactionType,
                    LocalDateTime.now()
            );
            // check transaction was created successfully
            if (transaction.isPresent()) {
                transactionResponse.setApproved(true);
            }
        }
        return transactionResponse;
    }

    private static AccountCreationResponse getAccountCreation(String requestBody) {
        AccountCreationRequest accountCreation = gson.fromJson(requestBody, AccountCreationRequest.class);
        String userId = accountCreation.getUserId();
        String accountName = accountCreation.getAccountName();
        if (accountName == null || accountName.isEmpty()) {
            accountName = "Credit Card Account";
        }
        int salary = accountCreation.getSalary();
        Optional<CreditCardAccount> creditCardAccountOptional = new AccountCreator()
                .createNewCreditCardAccount(accountName, userId, salary);
        AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
        if (creditCardAccountOptional.isPresent()) {
            accountCreationResponse.setAccountNumber(creditCardAccountOptional.get().getAccountNumber());
            accountCreationResponse.setIsApproved(true);
        } else {
            accountCreationResponse.setIsApproved(false);
        }
        return accountCreationResponse;
    }

    private static CreateStatementResponse getCreateStatement(String requestBody) {
        CreateStatementRequest createStatement = gson.fromJson(requestBody, CreateStatementRequest.class);
        String accountId = createStatement.getAccountId();
        String authToken = createStatement.getAuthToken();

        CreateStatementResponse createStatementResponse = new CreateStatementResponse();

        return createStatementResponse;
    }

    private static EmailNotificationsResponse getEmailNotifications(String requestBody) {
        EmailNotificationsRequest emailNotifications = gson.fromJson(requestBody, EmailNotificationsRequest.class);
        String accountId = emailNotifications.getAccountId();
        String emailAddress = emailNotifications.getEmailAddress();

        EmailNotificationsResponse emailNotificationsResponse = new EmailNotificationsResponse();

        return emailNotificationsResponse;
    }

    private static FetchStatementResponse getFetchStatementPeriod(String requestBody) {
        FetchStatementRequest fetchStatement = gson.fromJson(requestBody, FetchStatementRequest.class);
        String accountId = fetchStatement.getAccountId();
        Optional<Statement> latestStatement = new CreditCardStatementFetcher().getLatestStatement(accountId);
        FetchStatementResponse fetchStatementResponse = new FetchStatementResponse();
        if (latestStatement.isPresent()) {
            fetchStatementResponse.setAccountId(latestStatement.get().getAccountId());
        }
        return fetchStatementResponse;
    }

    private static CheckCreditLineResponse getCheckCreditLine(String requestBody) {
        CheckCreditLineRequest checkCreditLine = gson.fromJson(requestBody, CheckCreditLineRequest.class);
        String ssn = checkCreditLine.getSsn();
        Optional<CreditReport> creditReport = new CreditReportFetcher().loadCreditReport(ssn);
        CheckCreditLineResponse checkCreditLineResponse = new CheckCreditLineResponse();
        checkCreditLineResponse.getAvailableCredit();
        return checkCreditLineResponse;
    }

    private static AccountClosureResponse getAccountClosure(String requestBody) {
        AccountClosureRequest accountClosure = gson.fromJson(requestBody, AccountClosureRequest.class);
        String authToken = accountClosure.getAuthToken();
        String emailAddress = accountClosure.getEmailAddress();
        String accountId = accountClosure.getAccountId();

        AccountClosureResponse accountClosureResponse = new AccountClosureResponse();

        return accountClosureResponse;
    }

    // What is this method looking for? CreditLimitCheck is not containing credit limit
    private static CreditLimitCheck getCreditLimitCheck(String requestBody) {
        CreditLimitCheck creditLimitCheck = gson.fromJson(requestBody, CreditLimitCheck.class);
        String accountId = creditLimitCheck.getAccountId();
        String ssn = creditLimitCheck.getSsn();
        int creditScore = creditLimitCheck.getCreditScore();
        int salary = creditLimitCheck.getSalary();
        
        Optional<CreditCardAccount> creditCardAccountOptional = AccountFetcher.loadCreditCardAccount(accountId);
        if (creditCardAccountOptional.isPresent()) {
        	// Want to add credit limit to some attribute and return?
        } 
        
        creditLimitCheck.setAccountId(accountId);
        creditLimitCheck.setSsn(ssn);
        creditLimitCheck.setSalary(salary);
        
        return creditLimitCheck;
    }

    private static ListTransactionsResponse getListTransactions(String requestBody) {
        ListTransactionsRequest listTransactions = gson.fromJson(requestBody, ListTransactionsRequest.class);
        String authToken = listTransactions.getAuthToken();
        LocalDateTime startTime = listTransactions.getStartTime();
        LocalDateTime endTime = listTransactions.getEndTime();
        // Transaction newListTransaction = new TransactionFetcher().loadPostedTransactions(authToken, startTime, endTime);
        ListTransactionsResponse listTransactionsResponse = new ListTransactionsResponse();
        // listTransactionsResponse.setTransactions(transactions);
        return listTransactionsResponse;
    }

    private static BalanceCheckResponse getBalanceCheck(String requestBody) {
        BalanceCheckRequest balanceCheck = gson.fromJson(requestBody, BalanceCheckRequest.class);
        String authToken = balanceCheck.getAuthToken();
        double balance = balanceCheck.getBalance();
        BalanceCheckResponse balanceCheckResponse = new BalanceCheckResponse();
        // To-do
        return balanceCheckResponse;
    }

    private static AccountLoginResponse getAccountLogin(String requestBody) {
        AccountLoginRequest accountLogin = gson.fromJson(requestBody, AccountLoginRequest.class);
        String emailAddress = accountLogin.getEmailAddress();
        String password = accountLogin.getPassword();
        String authToken = "";
        if (emailAddress != null && password != null) {
            // TODO - fetch auth token
            authToken = "MyAuthToken";
        }
        AccountLoginResponse accountLoginResponse = new AccountLoginResponse();
        accountLoginResponse.setAuthToken(authToken);
        return accountLoginResponse;
    }

    private static UserApplicationResponse getUserApplication(String requestBody) {
        UserApplicationRequest userApplication = gson.fromJson(requestBody, UserApplicationRequest.class);
        int age = userApplication.getAge();
        String ssn = userApplication.getSsn();
        String validEmail = userApplication.getValidEmail();
        // UserApprover userApprover = new UserApprover().isApproved(age, ssn, validEmail);
        UserApplicationResponse userApplicationResponse = new UserApplicationResponse();
        if (userApplicationResponse.getIsValid()) {
            userApplicationResponse.setIsValid(true);
            System.out.println("You are approved!");
        } else {
            userApplicationResponse.setIsValid(false);
            System.out.println("You are not approved!");
        }
        return userApplicationResponse;
    }

    private static RedeemRewardsResponse getRedeemRewards(String requestBody) {
		RedeemRewardsRequest redeemRewardsRequest = gson.fromJson(requestBody, RedeemRewardsRequest.class);
		String accountId = redeemRewardsRequest.getAccountId();
		int amount = redeemRewardsRequest.getAmount();
		
		RedeemRewardsResponse redeemRewardsResponse = new RedeemRewardsResponse();
		int previousRewards = RewardFetcher.getCurrentRewardPoints(accountId);
		new RewardRedeemer().redeemPoints(amount, accountId);
		int updatedRewards = RewardFetcher.getCurrentRewardPoints(accountId);
		if (updatedRewards == previousRewards - amount) {
			redeemRewardsResponse.setRedeemed(true);
		} else {
			redeemRewardsResponse.setRedeemed(false);
		}
		return redeemRewardsResponse;
	}
}