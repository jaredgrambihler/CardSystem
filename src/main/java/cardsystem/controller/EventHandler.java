package cardsystem.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class EventHandler {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String handleEvent(HashMap<String, Object> event) {
        String action = (String) event.getOrDefault("action", "unknown");
        String body = (String) event.getOrDefault("body", "");
        Object responseJson = RequestHandler.handleRequest(action, body);
        // Sending back event for testing
        JsonObject json = new JsonObject();
        json.addProperty("event", String.valueOf(event));
        responseJson = json;
        return gson.toJson(responseJson);
    }

}
