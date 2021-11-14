package cardsystem.controller;

import cardsystem.account.AccountCreator;
import cardsystem.account.CreditCardAccount;
import cardsystem.approval.UserApprover;
import cardsystem.database.models.Account;
import cardsystem.email.CustomerEmail;
import cardsystem.email.Email;
import cardsystem.models.*;
import cardsystem.transaction.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.Optional;

public class RequestHandler {

    private static final Gson gson = new GsonBuilder().create();

    public static Object handleRequest(String action, String requestBody) {
        Object jsonObject = new JsonObject();
        switch (action) {
        case "BalancePayment":
            jsonObject = getBalancePayment(requestBody);
        case "MerchantTransaction":
            jsonObject = getCreateStatement(requestBody);
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
            jsonObject = getAccountLoginResponse(requestBody);
        case "AccountClosure":
            jsonObject = getAccountClosure(requestBody);
        case "CreateStatement":
            jsonObject = getMakeStatement(requestBody);
        case "UserApplication":
            jsonObject = getUserApplicationRequest(requestBody);
        case "AccountCreation":
            jsonObject = getAccountCreation(requestBody);
        default:
            // Unknown action
            break;
        }
        return jsonObject;
    }

    private static AccountCreationResponse getAccountCreation(String requestBody){
        AccountCreation accountCreation = gson.fromJson(requestBody, AccountCreation.class);
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

    private static CreateStatementRequest getMakeStatement(String requestBody) {
        CreateStatementRequest makeStatement = gson.fromJson(requestBody, CreateStatementRequest.class);
        String authToken = makeStatement.getAuthToken();
        String statementPeriodEnd = makeStatement.getStatementPeriodEnd();
        //To-do
        return makeStatement;
    }

    private static EmailNotificationsResponse getEmailNotifications(String requestBody) {
        EmailNotificationsRequest emailNotifications = gson.fromJson(requestBody, EmailNotificationsRequest.class);
        String authToken = emailNotifications.getAuthToken();
        CustomerEmail customerEmail = new CustomerEmail(toAddress, subject, header, body, footer);
        EmailNotificationsResponse customerEmailResponse = new EmailNotificationsResponse();
        return customerEmailResponse;
    }

    private static FetchStatementRe getFetchStatementPeriod(String requestBody) {
        FetchStatement fetchStatementPeriod = gson.fromJson(requestBody, FetchStatement.class);
        String startDate = fetchStatementPeriod.getStartDate();
        String endDate = fetchStatementPeriod.getEndDate();
        //To-do
        return fetchStatementPeriod;
    }

    private static CheckCreditLine getCheckCreditLine(String requestBody) {
        CheckCreditLine checkCreditLine = gson.fromJson(requestBody, CheckCreditLine.class);
        String accountId = checkCreditLine.getAccountId();
        int creditLines = checkCreditLine.getCreditLines();
        //To-do
        return checkCreditLine;
    }

    private static AccountClosure getAccountClosure(String requestBody) {
        AccountClosure accountClosure = gson.fromJson(requestBody, AccountClosure.class);
        String authToken = accountClosure.getAuthToken();
        String emailAddress = accountClosure.getEmailAddress();
        String accountId = accountClosure.getAccountId();
        //To-do
        return accountClosure;
    }

    private static ListTransactionsResponse getListTransactions(String requestBody) {
        ListTransactionsRequest listTransactions = gson.fromJson(requestBody, ListTransactionsRequest.class);
        String authToken = listTransactions.getAuthToken();
        LocalDateTime startTime = listTransactions.getStartTime();
        LocalDateTime endTime = listTransactions.getEndTime();
        Transaction newListTransaction = new TransactionFetcher().loadPostedTransactions(authToken, startTime, endTime);
        ListTransactionsResponse listTransactionsResponse = new ListTransactionsResponse();
        return listTransactionsResponse;
    }

    private static BalanceCheckResponse getBalanceCheck(String requestBody) {
        BalanceCheckRequest balanceCheck = gson.fromJson(requestBody, BalanceCheckRequest.class);
        String authToken = balanceCheck.getAuthToken();
        double balance = balanceCheck.getBalance();
        BalanceCheckResponse balanceCheckResponse = new BalanceCheckResponse();
        //To-do
        return balanceCheckResponse;
    }

    private static CashAdvanceTransactionResponse getCashAdvanceTransaction(String requestBody) {
        CashAdvanceTransactionRequest cashAdvanceTransaction = gson.fromJson(requestBody, CashAdvanceTransactionRequest.class);
        String authToken = cashAdvanceTransaction.getAuthToken();
        double amount = cashAdvanceTransaction.getAmount();
        String transactionDate = cashAdvanceTransaction.getTransactionDate();
        String postedDate = cashAdvanceTransaction.getPostedDate();
        Transaction NewCashAdvanceTransaction = new TransactionCreator().createCashAdvanceTransaction(accountId, amount, transactionDate);
        CashAdvanceTransactionResponse cashAdvanceTransactionResponse = new CashAdvanceTransactionResponse();
        return cashAdvanceTransactionResponse;
    }

    private static AccountLoginResponse getAccountLoginResponse(String requestBody) {
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

    private static UserApplicationResponse getUserApplicationRequest(String requestBody) {
        UserApplicationRequest userApplication = gson.fromJson(requestBody, UserApplicationRequest.class);
        int age = userApplication.getAge();
        String ssn = userApplication.getSsn();
        String validEmail = userApplication.getValidEmail();
        UserApprover userApprover = new UserApprover().isApproved(age, ssn, validEmail)
        UserApplicationResponse userApplicationResponse = new UserApplicationResponse();
        if(userApplicationResponse.getIsValid()== true){
            System.out.println("You are approved!");
        } else {
            System.out.println("You are not approved!");
        }
        return userApplicationResponse;
    }

    private static BalancePayment getBalancePayment(String requestBody) {
        BalancePayment balancePayment = gson.fromJson(requestBody, BalancePayment.class);
        double balance = balancePayment.getBalance();
        String authToken = balancePayment.getAuthToken();
        Transaction paymentTransaction = new TransactionCreator().createPayment(accountId, amount, transactionDate);
        BalancePayment balancePaymentResponse = new BalancePayment();
        return balancePaymentResponse;
    }

    private static MerchantTransactionResponse getMerchantTransactionRequest(String requestBody) {
        MerchantTransactionRequest merchantTransaction = gson.fromJson(requestBody, MerchantTransactionRequest.class);
        double amount = merchantTransaction.getAmount();
        String authToken = merchantTransaction.getAuthToken();
        String transactionDate = merchantTransaction.getTransactionDate();
        String merchant = merchantTransaction.getMerchant();
        Transaction transactionOptional = new TransactionCreator()
        .createMerchantTransaction(accountId, amount, transactionDate, merchant);
        MerchantTransactionResponse merchantTransactionResponse = new MerchantTransactionResponse();
        if (merchantTransactionResponse.getIsApproved()); {
            merchantTransactionResponse.setIsApproved(true);
        } else {
            merchantTransactionResponse.setIsApproved(false);
        }
        return merchantTransactionResponse;
    }
}