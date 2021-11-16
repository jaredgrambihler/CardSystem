package cardsystem.controller;

import cardsystem.models.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EventHandler {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String handleEvent(String requestBody) {
        Action actionModel = gson.fromJson(requestBody, Action.class);
        String action = actionModel.getAction();
        Object responseJson = RequestHandler.handleRequest(action, requestBody);
        return gson.toJson(responseJson);
    }

}
