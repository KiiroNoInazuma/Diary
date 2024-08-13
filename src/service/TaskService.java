package service;

import entities.Task;
import entities.Type;

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

    public List<Task> getAllTypeTask(Type type) {
        List<Task> list = taskMap.values().stream().filter(s -> Objects.equals(s.getType(), type)).toList();
        return list.isEmpty() ? null : list;
    }

    public void allTask() {
        System.out.println(taskMap.values().isEmpty() ? "Ни одной задачи не было найдено." : taskMap.values());
    }
}
