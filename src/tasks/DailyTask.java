package tasks;

import entities.Task;

import java.time.LocalDate;

public class DailyTask extends Task {
    @Override
    protected boolean appearsIn(LocalDate date) {
        return false;
    }
}
