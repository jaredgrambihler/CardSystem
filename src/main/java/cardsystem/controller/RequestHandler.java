package cardsystem.controller;

import com.google.gson.JsonObject;

public class RequestHandler {

    public static JsonObject handleRequest(String action, String requestBody) {
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
            case "AccountClosure":
            case "CreateStatement":
            default:
                // Unknown action
                break;
        }
        return new JsonObject();
    }
}
