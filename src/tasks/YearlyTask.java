package tasks;

import entities.Task;
import entities.Type;

import java.time.LocalDate;

public class YearlyTask extends Task {
    public YearlyTask(String title, Type type, String description) {
        super(title, type, description);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return false;
    }
}
