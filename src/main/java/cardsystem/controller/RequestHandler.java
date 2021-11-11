package cardsystem.controller;

import cardsystem.email.Email;
import cardsystem.models.AccountApply;
import cardsystem.models.AccountClosure;
import cardsystem.models.AccountCreation;
import cardsystem.models.AccountLogin;
import cardsystem.models.AccountLoginResponse;
import cardsystem.models.BalanceCheck;
import cardsystem.models.BalancePayment;
import cardsystem.models.CashAdvanceTransaction;
import cardsystem.models.CheckCreditLine;
import cardsystem.models.CreditLimitCheck;
import cardsystem.models.EmailNotifications;
import cardsystem.models.FetchStatementPeriod;
import cardsystem.models.ListTransactions;
import cardsystem.models.MakeStatement;
import cardsystem.models.MakeTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class RequestHandler {

    private static final Gson gson = new GsonBuilder().create();

    public static Object handleRequest(String action, String requestBody) {
        Object jsonObject = new JsonObject();
        switch (action) {
        case "BalancePayment":
            jsonObject = getBalancePayment(requestBody);
        case "MerchantTransaction":
            jsonObject = getMakeTransaction(requestBody);
        case "CashAdvanceTransaction":
            jsonObject = getCashAdvanceTransaction(requestBody);
        case "CheckBalance":
            jsonObject = getBalanceCheck(requestBody);
        case "CheckCreditLine":
            jsonObject = getCheckCreditLine(requestBody);
        case "CreditLimitCheck":
            jsonObject = getCreditLimitCheck(requestBody);
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
        case "AccountApply":
            jsonObject = getAccountApply(requestBody);
        case "AccountCreation":
            jsonObject = getAccountCreation(requestBody);
        default:
            // Unknown action
            break;
        }
        return jsonObject;
    }

    private static AccountCreation getAccountCreation(String requestBody){
        AccountCreation accountCreation = gson.fromJson(requestBody, AccountCreation.class);
        String userId = accountCreation.getUserId();
        String accountName = accountCreation.getAccountName();
        String accountNumber = accountCreation.getAccountId();
        String accountId = accountCreation.getAccountId();
        return accountCreation;
    }
    private static MakeStatement getMakeStatement(String requestBody) {
        MakeStatement makeStatement = gson.fromJson(requestBody, MakeStatement.class);
        String accountId = makeStatement.getAccountId();
        String statementPeriodStart = makeStatement.getStatementPeriodStart();
        String statementPeriodEnd = makeStatement.getStatementPeriodEnd();
        double amount = makeStatement.getAmount();
        double balance = makeStatement.getBalance();
        String transactionId = makeStatement.getTransactionId();
        return makeStatement;
    }

    private static EmailNotifications getEmailNotifications(String requestBody) {
        EmailNotifications emailNotifications = gson.fromJson(requestBody, EmailNotifications.class);
        String emailAddress = emailNotifications.getEmailAddress();
        String accountId = emailNotifications.getAccountId();
        return emailNotifications;
    }

    private static FetchStatementPeriod getFetchStatementPeriod(String requestBody) {
        FetchStatementPeriod fetchStatementPeriod = gson.fromJson(requestBody, FetchStatementPeriod.class);
        String startDate = fetchStatementPeriod.getStartDate();
        String endDate = fetchStatementPeriod.getEndDate();
        return fetchStatementPeriod;
    }

    private static CheckCreditLine getCheckCreditLine(String requestBody) {
        CheckCreditLine checkCreditLine = gson.fromJson(requestBody, CheckCreditLine.class);
        String accountId = checkCreditLine.getAccountId();
        int creditLines = checkCreditLine.getCreditLines();
        return checkCreditLine;
    }

    private static AccountClosure getAccountClosure(String requestBody) {
        AccountClosure accountClosure = gson.fromJson(requestBody, AccountClosure.class);
        String accountId = accountClosure.getAccountId();
        String emailAddress = accountClosure.getEmailAddress();
        return accountClosure;
    }

    private static CreditLimitCheck getCreditLimitCheck(String requestBody) {
        CreditLimitCheck creditLimitCheck = gson.fromJson(requestBody, CreditLimitCheck.class);
        String accountId = creditLimitCheck.getAccountId();
        String ssn = creditLimitCheck.getSsn();
        int creditScore = creditLimitCheck.getCreditScore();
        int salary = creditLimitCheck.getSalary();
        return creditLimitCheck;
    }

    private static ListTransactions getListTransactions(String requestBody) {
        ListTransactions listTransactions = gson.fromJson(requestBody, ListTransactions.class);
        String accountId = listTransactions.getAccountId();
        double amount = listTransactions.getAmount();
        String transactionDate = listTransactions.getTransactionDate();
        String transactionId = listTransactions.getTransactionId();
        return listTransactions;
    }

    private static BalanceCheck getBalanceCheck(String requestBody) {
        BalanceCheck balanceCheck = gson.fromJson(requestBody, BalanceCheck.class);
        String accountId = balanceCheck.getAccountId();
        double balance = balanceCheck.getBalance();
        return balanceCheck;
    }

    private static CashAdvanceTransaction getCashAdvanceTransaction(String requestBody) {
        CashAdvanceTransaction cashAdvanceTransaction = gson.fromJson(requestBody, CashAdvanceTransaction.class);
        String accountId = cashAdvanceTransaction.getAccountId();
        double amount = cashAdvanceTransaction.getAmount();
        String transactionDate = cashAdvanceTransaction.getTransactionDate();
        String postedDate = cashAdvanceTransaction.getPostedDate();
        return cashAdvanceTransaction;
    }

    private static AccountLoginResponse getAccountLoginResponse(String requestBody) {
        AccountLogin accountLogin = gson.fromJson(requestBody, AccountLogin.class);
        String emailAddress = accountLogin.getEmailAdress();
        String password = accountLogin.getPassword();
        if (emailAddress || password) {
            return null;
        }
        // add null check, return some kind of error if a field isn't specified
        // TODO - get auth token
        String authToken = "myAuthToken";
        AccountLoginResponse accountLoginResponse = new AccountLoginResponse();
        accountLoginResponse.setAuthToken(authToken);
        return accountLoginResponse;
    }

    private static AccountApply getAccountApply(String requestBody) {
        AccountApply accountApply = gson.fromJson(requestBody, AccountApply.class);
        int age = accountApply.getAge();
        String ssn = accountApply.getSsn();
        String validEmail = accountApply.getValidEmail();
        AccountApplyResponse accountReplyResponse = new AccountReplyResponse();
        accountReplyResponse.
        return accountReplyResponse;
    }

    private static BalancePayment getBalancePayment(String requestBody) {
        BalancePayment balancePayment = gson.fromJson(requestBody, BalancePayment.class);
        // id declaration?
        double amount = balancePayment.getAmount();
        String accountId = balancePayment.getAccountId();
        String transactionDate = balancePayment.getTransactionDate();
        String postedDate = balancePayment.getPostedDate();
        return balancePayment;
    }

    private static MakeTransaction getMakeTransaction(String requestBody) {
        MakeTransaction makeTransaction = gson.fromJson(requestBody, MakeTransaction.class);
        double amount = makeTransaction.getAmount();
        String accountId = makeTransaction.getAccountId();
        String transactionDate = makeTransaction.getTransactionDate();
        String merchant = makeTransaction.getMerchant();
        return makeTransaction;
    }
}