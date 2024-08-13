package entities;

import utils.TimeParse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {

    private static int idGenerator;
    private String title;
    private final Type type;
    private final int id;
    private final LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, String description) {
        id = ++idGenerator;
        this.title = title;
        this.type = type;
        this.dateTime = LocalDateTime.now();
        this.description = description;
    }

    public abstract boolean appearsIn(LocalDate date);

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id && Objects.equals(title, task.title) && type == task.type
                && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }

    @Override
    public String toString() {
        return String.format(
                """
                       \s
                        ------------------------------
                         id = %s
                         title = %s
                         type = %s
                         dateTime = %s
                         description = %s
                        ------------------------------
               \s""",
                id, title, type, TimeParse.parseDateTaskShow(dateTime), description
        );
    }
}
