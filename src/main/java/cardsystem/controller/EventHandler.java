package cardsystem.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class EventHandler {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String handleEvent(HashMap<String, Object> event) {
        String action = (String) event.get("action");
        String body = (String) event.get("body");
        JsonObject responseJson = RequestHandler.handleRequest(action, body);
        return gson.toJson(responseJson);
    }

}
