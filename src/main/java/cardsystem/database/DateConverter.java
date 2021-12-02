package cardsystem.database;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Convert between date objects and strings used in the db **/
public class DateConverter {

    public static String getIso8601Timestamp(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return date.format(formatter);
    }

    public static LocalDateTime getLocalDateTime(String iso8601Date) {
        return LocalDateTime.parse(iso8601Date);
    }
    
    public static String getIso8601Date(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public static LocalDate getLocalDate(String iso8601Date) {
        return LocalDate.parse(iso8601Date);
    }
    
}
