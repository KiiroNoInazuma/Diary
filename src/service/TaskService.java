package service;

import entities.Task;
import utils.TimeParse;

import java.time.LocalDate;
import java.util.*;

public class TaskService {

    public static final Map<Integer, Task> taskMap = new HashMap<>();
    private Collection<Task> removedTasks;

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove(int idTask) {
        return taskMap.remove(idTask);
    }

    public List<Task> getAllByDate(LocalDate date) {
        return taskMap.values().stream()
                .filter(task ->
                        Objects.equals(TimeParse.parseDateTask(task.getDateTime()), TimeParse.parseDateTask(date.atStartOfDay())))
                .toList();
    }
}
