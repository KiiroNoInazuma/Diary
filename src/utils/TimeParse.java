package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface TimeParse {

    String defaultPattern = "dd.MM.yyyy";

    static String parseDateTask(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(defaultPattern));
    }

    static String parseDateTaskShow(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(defaultPattern + " HH:mm"));
    }
}
