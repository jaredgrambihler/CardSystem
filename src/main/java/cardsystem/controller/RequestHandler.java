package cardsystem.controller;

import cardsystem.models.AccountLogin;
import cardsystem.models.AccountLoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class RequestHandler {

    private static final Gson gson = new GsonBuilder().create();

    public static Object handleRequest(String action, String requestBody) {
        Object jsonObject = new JsonObject();
        switch(action) {
            case "BalancePayment":
            case "MerchantTransaction":
            case "CashAdvanceTransaction":
            case "CheckBalance":
            case "GetAvailableCreditLine":
            case "GetCreditLimit":
            case "ListTransactions":
            case "FetchStatementPeriod":
            case "AccountApplication":
            case "AccountLogin":
                jsonObject = getAccountLoginResponse(requestBody);
            case "AccountClosure":
            case "CreateStatement":
            default:
                // Unknown action
                break;
        }
        return jsonObject;
    }

    private static AccountLoginResponse getAccountLoginResponse(String requestBody) {
        AccountLogin accountLogin = gson.fromJson(requestBody, AccountLogin.class);
        String emailAddress = accountLogin.getEmailAdress();
        String password = accountLogin.getPassword();
        // add null check, return some kind of error if a field isn't specified
        // TODO - get auth token
        String authToken = "myAuthToken";
        AccountLoginResponse accountLoginResponse = new AccountLoginResponse();
        accountLoginResponse.setAuthToken(authToken);
        return accountLoginResponse;
    }
}
