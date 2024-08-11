package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface TimeParse {

    DateTimeFormatter defaultPattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    DateTimeFormatter defaultPatternWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    static String parseDateTask(LocalDateTime time) {
        return time.format(defaultPattern);
    }
    static LocalDate parseDateTask(String date) {
        return LocalDate.parse(date, defaultPattern);
    }

    static String parseDateTaskShow(LocalDateTime time) {
        return time.format(defaultPatternWithTime);
    }
}
