package tasks;

import entities.Task;
import entities.Type;
import utils.TimeParse;

import java.time.LocalDate;
import java.util.Objects;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, String description) {
        super(title, type, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return Objects.equals(TimeParse.parseDateTask(date.atStartOfDay()), TimeParse.parseDateTask(getDateTime()));
    }
}
