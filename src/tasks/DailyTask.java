package tasks;

import entities.Task;
import entities.Type;

import java.time.LocalDate;
import java.util.Objects;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, String description) {
        super(title, type, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return Objects.equals(date.atStartOfDay().getYear(), getDateTime().getYear());
    }
}
