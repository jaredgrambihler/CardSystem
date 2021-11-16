package cardsystem.controller;

import cardsystem.account.AccountCreator;
import cardsystem.account.CreditCardAccount;
import cardsystem.approval.UserApprover;
import cardsystem.creditbureau.CreditReport;
import cardsystem.creditbureau.ExperianCreditReport;
import cardsystem.database.models.Account;
import cardsystem.email.AccountEmailDecorator;
import cardsystem.email.AwsSesEmailSender;
import cardsystem.email.CustomerEmail;
import cardsystem.email.Email;
import cardsystem.email.EmailSenderFactory;
import cardsystem.models.*;
import cardsystem.statement.CreditCardStatement;
import cardsystem.statement.CreditCardStatementFetcher;
import cardsystem.statement.Statement;
import cardsystem.statement.StatementPeriod;
import cardsystem.transaction.*;

import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RequestHandler {

    private static final Gson gson = new GsonBuilder().create();

    public static Object handleRequest(String action, String requestBody) {
        Object jsonObject = new JsonObject();
        switch (action) {
        case "BalancePayment":
            jsonObject = getBalancePayment(requestBody);
        case "MerchantTransaction":
            jsonObject = getMerchantTransaction(requestBody);
        case "CashAdvanceTransaction":
            jsonObject = getCashAdvanceTransaction(requestBody);
        case "CheckBalance":
            jsonObject = getBalanceCheck(requestBody);
        case "CheckCreditLine":
            jsonObject = getCheckCreditLine(requestBody);
        case "ListTransactions":
            jsonObject = getListTransactions(requestBody);
        case "FetchStatementPeriod":
            jsonObject = getFetchStatementPeriod(requestBody);
        case "EmailNotifications":
            jsonObject = getEmailNotifications(requestBody);
        case "AccountLogin":
            jsonObject = getAccountLogin(requestBody);
        case "AccountClosure":
            jsonObject = getAccountClosure(requestBody);
        case "CreateStatement":
            jsonObject = getCreateStatement(requestBody);
        case "UserApplication":
            jsonObject = getUserApplication(requestBody);
        case "AccountCreation":
            jsonObject = getAccountCreation(requestBody);
        default:
            // Unknown action
            break;
        }
        return jsonObject;
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
        List<Integer> creditLines = checkCreditLine.getCreditLines();
        int score = checkCreditLine.getScore();
        CreditReport creditReport = new ExperianCreditReport(ssn, score, creditLines);
        CheckCreditLineResponse checkCreditLineResponse = new CheckCreditLineResponse();
        checkCreditLineResponse.getAvailableCredit(creditLines);
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

    private static ListTransactionsResponse getListTransactions(String requestBody) {
        ListTransactionsRequest listTransactions = gson.fromJson(requestBody, ListTransactionsRequest.class);
        String authToken = listTransactions.getAuthToken();
        LocalDateTime startTime = listTransactions.getStartTime();
        LocalDateTime endTime = listTransactions.getEndTime();
        Transaction newListTransaction = new TransactionFetcher().loadPostedTransactions(authToken, startTime, endTime);
        ListTransactionsResponse listTransactionsResponse = new ListTransactionsResponse();
        listTransactionsResponse.setTransactions(transactions);
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

    private static CashAdvanceTransactionResponse getCashAdvanceTransaction(String requestBody) {
        CashAdvanceTransactionRequest cashAdvanceTransaction = gson.fromJson(requestBody,
                CashAdvanceTransactionRequest.class);
        String authToken = cashAdvanceTransaction.getAuthToken();
        double amount = cashAdvanceTransaction.getAmount();
        String accountId = cashAdvanceTransaction.getAccountId();
        String transactionDate = cashAdvanceTransaction.getTransactionDate();
        String postedDate = cashAdvanceTransaction.getPostedDate();
        Transaction NewCashAdvanceTransaction = new TransactionCreator().createCashAdvanceTransaction(accountId, amount, transactionDate)
        CashAdvanceTransactionResponse cashAdvanceTransactionResponse = new CashAdvanceTransactionResponse();
        // To-do
        return cashAdvanceTransactionResponse;
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
        UserApprover userApprover = new UserApprover().isApproved(age, ssn, validEmail);
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

    private static BalancePayment getBalancePayment(String requestBody) {
        BalancePayment balancePayment = gson.fromJson(requestBody, BalancePayment.class);
        double balance = balancePayment.getBalance();
        String authToken = balancePayment.getAuthToken();

        BalancePayment balancePaymentResponse = new BalancePayment();

        return balancePaymentResponse;
    }

    private static MerchantTransactionResponse getMerchantTransaction(String requestBody) {
        MerchantTransactionRequest merchantTransaction = gson.fromJson(requestBody, MerchantTransactionRequest.class);
        double amount = merchantTransaction.getAmount();
        String accountId = merchantTransaction.getAccountId();
        String authToken = merchantTransaction.getAuthToken();
        String transactionDate = merchantTransaction.getTransactionDate();
        String merchant = merchantTransaction.getMerchant();
        Optional<Transaction> newTransaction = new TransactionCreator().createMerchantTransaction(accountId, amount, transactionDate, merchant);
        MerchantTransactionResponse merchantTransactionResponse = new MerchantTransactionResponse();
        if (merchantTransactionResponse.isValidAmount(amount)) {
            merchantTransactionResponse.setIsApproved(true);
        } else {
            merchantTransactionResponse.setIsApproved(false);
        }
        return merchantTransactionResponse;
    }
}