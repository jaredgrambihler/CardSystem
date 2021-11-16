package cardsystem;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class TestHandlerStream {

    @Test
    public void testGetStringFromReader() throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream( "Test string".getBytes())));
        String actual = new HandlerStream().getStringFromReader(reader);
        assertEquals("Test string", actual);
    }
}
