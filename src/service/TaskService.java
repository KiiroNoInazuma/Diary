package service;

import entities.Task;
import entities.Type;
import utils.TimeParse;

import java.time.LocalDate;
import java.util.*;

public class TaskService {

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private Collection<Task> removedTasks;

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove(int idTask) {
        return taskMap.remove(idTask);
    }

    public List<Task> getAllByDate(LocalDate date) {
        return taskMap.values().stream()
                .filter(task -> task.appearsIn(date))
                .toList();
    }

    public void getAllTypeTask(Type type) {
        taskMap.values().stream().filter(s -> Objects.equals(s.getType(), type)).forEach(System.out::println);
    }
}
