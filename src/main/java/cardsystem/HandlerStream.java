package cardsystem;

import cardsystem.controller.EventHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.nio.charset.Charset;
import java.lang.IllegalStateException;

public class HandlerStream implements RequestStreamHandler {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException
    {
        LambdaLogger logger = context.getLogger();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("US-ASCII")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("US-ASCII"))));
        try
        {
            String responseString = EventHandler.handleEvent(getStringFromReader(reader));
            writer.write(responseString);
            if (writer.checkError())
            {
                logger.log("WARNING: Writer encountered an error.");
            }
        }
        catch (IllegalStateException | JsonSyntaxException exception)
        {
            logger.log(exception.toString());
        }
        finally
        {
            reader.close();
            writer.close();
        }
    }

    public String getStringFromReader(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        int curChar;
        while ((curChar = reader.read()) != -1) {
            builder.append((char) curChar);
        }
        return builder.toString();
    }
}
