package tasks;

import entities.Task;

import java.time.LocalDate;

public class YearlyTask extends Task {
    @Override
    protected boolean appearsIn(LocalDate date) {
        return false;
    }
}
