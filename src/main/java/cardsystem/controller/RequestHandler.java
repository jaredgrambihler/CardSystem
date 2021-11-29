package cardsystem.controller;

import cardsystem.account.AccountCreator;
import cardsystem.account.AccountFetcher;
import cardsystem.account.CreditCardAccount;
import cardsystem.auth.Token;
import cardsystem.auth.TokenFactory;
import cardsystem.balance.Balance;
import cardsystem.balance.BalanceFetcher;
import cardsystem.database.DateConverter;
import cardsystem.models.*;
import cardsystem.rewards.RewardFetcher;
import cardsystem.rewards.RewardRedeemer;
import cardsystem.statement.CreditCardStatement;
import cardsystem.statement.CreditCardStatementCreator;
import cardsystem.statement.CreditCardStatementFetcher;
import cardsystem.statement.Statement;
import cardsystem.statement.StatementPeriod;
import cardsystem.transaction.Transaction;
import cardsystem.transaction.TransactionCreator;
import cardsystem.transaction.TransactionFetcher;
import cardsystem.transaction.TransactionType;
import cardsystem.user.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
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
                jsonObject = checkBalance(requestBody);
                break;
            case "ListTransactions":
                jsonObject = getTransactions(requestBody);
                break;
            case "FetchStatementPeriod":
                jsonObject = fetchStatementPeriod(requestBody);
                break;
            case "UserLogin":
                jsonObject = userLogin(requestBody);
                break;
            case "AccountClosure":
                jsonObject = closeAccount(requestBody);
                break;
            case "CreateStatement":
                jsonObject = createStatement(requestBody);
                break;
            case "UserAccountApplication":
                jsonObject = submitUserAccountApplication(requestBody);
                break;
            case "CreditCardAccountApplication":
                jsonObject = submitCreditCardAccountApplication(requestBody);
                break;
            case "RedeemRewards":
            	jsonObject = redeemRewards(requestBody);
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
        Optional<Token> tokenOptional = TokenFactory.createToken(transactionRequest.getAuthToken());
        String accountId = transactionRequest.getAccountId();
        String counterparty = transactionRequest.getCounterparty();
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setApproved(false);

        // check arguments are valid for creating a transaction, else return
        if (accountId == null || counterparty == null || tokenOptional.isEmpty()) {
            return transactionResponse;
        }
        Token token = tokenOptional.get();
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

    private static AccountCreationResponse submitCreditCardAccountApplication(String requestBody) {
        AccountCreationRequest accountCreation = gson.fromJson(requestBody, AccountCreationRequest.class);
        String accountName = accountCreation.getAccountName();
        if (accountName == null || accountName.isEmpty()) {
            accountName = "Credit Card Account";
        }
        int salary = accountCreation.getSalary();
        Optional<Token> tokenOptional = TokenFactory.createToken(accountCreation.getAuthToken());
        AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
        accountCreationResponse.setIsApproved(false);
        if (tokenOptional.isPresent()) {
            String userId = tokenOptional.get().getUserId();
            Optional<CreditCardAccount> creditCardAccountOptional = new AccountCreator()
                    .createNewCreditCardAccount(accountName, userId, salary);
            if (creditCardAccountOptional.isPresent()) {
                accountCreationResponse.setAccountNumber(creditCardAccountOptional.get().getAccountNumber());
                accountCreationResponse.setIsApproved(true);
            }
        }
        return accountCreationResponse;
    }

    private static CreateStatementResponse createStatement(String requestBody) {
        CreateStatementRequest statementRequest = gson.fromJson(requestBody, CreateStatementRequest.class);
        Optional<Token> tokenOptional = TokenFactory.createToken(statementRequest.getAuthToken());
        String accountId = statementRequest.getAccountId();
        String startDate = statementRequest.getStartDate();
        String endDate = statementRequest.getEndDate();
        CreateStatementResponse statementResponse = new CreateStatementResponse();
        
        if (accountId == null || tokenOptional.isEmpty() || startDate == null || endDate == null) {
        	return statementResponse;
        }

        Token token = tokenOptional.get();
        if (token.getAccountIds().contains(accountId)) {
        	StatementPeriod statementPeriod = new StatementPeriod(DateConverter.getLocalDate(startDate), DateConverter.getLocalDate(endDate));
	        CreditCardStatement statement = new CreditCardStatementCreator().createStatement(accountId, statementPeriod);
	        statementResponse.setAccountId(accountId);
	        statementResponse.setBalance(statement.getBalance());
	        statementResponse.setTransactions(statement.getTransactions());
        }
        return statementResponse;
    }

    private static FetchStatementResponse fetchStatementPeriod(String requestBody) {
        FetchStatementRequest fetchStatementPeriodRequest = gson.fromJson(requestBody, FetchStatementRequest.class);
        String accountId = fetchStatementPeriodRequest.getAccountId();
        Optional<Token> tokenOptional = TokenFactory.createToken(fetchStatementPeriodRequest.getAuthToken());
        String date = fetchStatementPeriodRequest.getDate();
        
        FetchStatementResponse fetchStatementResponse = new FetchStatementResponse();
        if (tokenOptional.isPresent() && tokenOptional.get().getAccountIds().contains(accountId)) {
	        Optional<Statement> statement = new CreditCardStatementFetcher().getStatement(accountId, DateConverter.getLocalDateTime(date));
	        if (statement.isPresent()) {
	        	Statement creditCardStatement = statement.get();
	            fetchStatementResponse.setAccountId(creditCardStatement.getAccountId());
	            fetchStatementResponse.setBalance(creditCardStatement.getBalance());
	            fetchStatementResponse.setTransactions(creditCardStatement.getTransactions());
	        }
        }
        return fetchStatementResponse;
    }

    private static AccountClosureResponse closeAccount(String requestBody) {
        AccountClosureRequest accountClosure = gson.fromJson(requestBody, AccountClosureRequest.class);
        String authToken = accountClosure.getAuthToken();
        String accountId = accountClosure.getAccountId();
        boolean closed = false;
        Optional<Token> tokenOptional = TokenFactory.createToken(authToken);
        if (tokenOptional.isPresent() && tokenOptional.get().getAccountIds().contains(accountId)) {
            closed = new AccountCreator().closeAccount(accountId);
        }
        AccountClosureResponse accountClosureResponse = new AccountClosureResponse();
        accountClosureResponse.setClosed(closed);
        return accountClosureResponse;
    }

    private static ListTransactionsResponse getTransactions(String requestBody) {
        ListTransactionsRequest listTransactions = gson.fromJson(requestBody, ListTransactionsRequest.class);
        Optional<Token> tokenOptional = TokenFactory.createToken(listTransactions.getAuthToken());
        String accountId = listTransactions.getAccountId();

        ListTransactionsResponse listTransactionsResponse = new ListTransactionsResponse();
        if (tokenOptional.isEmpty() || !tokenOptional.get().getAccountIds().contains(accountId)) {
            return listTransactionsResponse;
        }
        LocalDateTime startTime = DateConverter.getLocalDateTime(listTransactions.getStartTime());
        LocalDateTime endTime = DateConverter.getLocalDateTime(listTransactions.getEndTime());
        List<Transaction> transactions = new TransactionFetcher().loadPostedTransactions(accountId, startTime, endTime);
        listTransactionsResponse.setTransactions(transactions);
        return listTransactionsResponse;
    }

    private static BalanceCheckResponse checkBalance(String requestBody) {
        BalanceCheckRequest balanceCheck = gson.fromJson(requestBody, BalanceCheckRequest.class);
        Optional<Token> tokenOptional = TokenFactory.createToken(balanceCheck.getAuthToken());
        String accountId = balanceCheck.getAccountId();
        
        BalanceCheckResponse balanceCheckResponse = new BalanceCheckResponse();
        if (tokenOptional.isPresent() && tokenOptional.get().getAccountIds().contains(accountId)) {
            Balance balance = BalanceFetcher.getLatestBalance(accountId);
            balanceCheckResponse.setBalance(balance.getBalance());
            balanceCheckResponse.setAvailableCredit(balance.getAvailableCredit());
            Optional<CreditCardAccount> account = AccountFetcher.loadCreditCardAccount(accountId);
            if (account.isPresent()) {
            	 balanceCheckResponse.setCreditLimit(account.get().getCreditLimit());
            }
        }
        return balanceCheckResponse;
    }

    private static AccountLoginResponse userLogin(String requestBody) {
        AccountLoginRequest accountLogin = gson.fromJson(requestBody, AccountLoginRequest.class);
        String emailAddress = accountLogin.getEmailAddress();
        String password = accountLogin.getPassword();
        String authToken = "";
        if (emailAddress != null && password != null) {
            Optional<Token> tokenOptional = TokenFactory.getLoginToken(emailAddress, password);
            if (tokenOptional.isPresent()) {
                authToken = tokenOptional.get().encode();
            }
        }
        AccountLoginResponse accountLoginResponse = new AccountLoginResponse();
        accountLoginResponse.setAuthToken(authToken);
        return accountLoginResponse;
    }

    private static UserApplicationResponse submitUserAccountApplication(String requestBody) {
        UserApplicationRequest userApplication = gson.fromJson(requestBody, UserApplicationRequest.class);
        String ssn = userApplication.getSsn();
        String email = userApplication.getEmail();
        String name = userApplication.getName();
        String birthDate = userApplication.getBirthDate();
        BigInteger income = userApplication.getIncome();
        UserApplicationResponse userApplicationResponse = new UserApplicationResponse();
        userApplicationResponse.setApproved(false);
        
        Optional<User> user = new UserCreator().createNewUser(name, ssn, email, income, DateConverter.getLocalDate(birthDate));
        if (user.isPresent()) {
        	userApplicationResponse.setApproved(true);
        } 
        return userApplicationResponse;
    }

    private static RedeemRewardsResponse redeemRewards(String requestBody) {
		RedeemRewardsRequest redeemRewardsRequest = gson.fromJson(requestBody, RedeemRewardsRequest.class);
		Optional<Token> tokenOptional = TokenFactory.createToken(redeemRewardsRequest.getAuthToken());
		String accountId = redeemRewardsRequest.getAccountId();
		int amount = redeemRewardsRequest.getAmount();
		
		RedeemRewardsResponse redeemRewardsResponse = new RedeemRewardsResponse();
		redeemRewardsResponse.setRedeemed(false);
		if (tokenOptional.isPresent() && tokenOptional.get().getAccountIds().contains(accountId)) {
			int previousRewards = RewardFetcher.getCurrentRewardPoints(accountId);
			new RewardRedeemer().redeemPoints(amount, accountId);
			int updatedRewards = RewardFetcher.getCurrentRewardPoints(accountId);
			if (updatedRewards == previousRewards - amount) {
				redeemRewardsResponse.setRedeemed(true);
			}
		}
		return redeemRewardsResponse;
	}
}