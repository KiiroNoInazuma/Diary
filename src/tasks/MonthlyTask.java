package tasks;

import entities.Task;
import entities.Type;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, Type type, String description) {
        super(title, type, description);
    }

    @Override
    protected boolean appearsIn(LocalDateTime date) {
        return false;
    }
}
