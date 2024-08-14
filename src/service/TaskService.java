package service;

import entities.Task;
import entities.Type;

import java.time.LocalDate;
import java.util.*;

public class TaskService {

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Collection<Task> removedTasks = new ArrayList<>();

    void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    Task remove(int idTask) {
        Task remove = taskMap.remove(idTask);
        if (remove != null) removedTasks.add(remove);
        return remove;
    }

    void allRemovedTasks() {
        System.out.println(removedTasks.isEmpty() ? "Нет задач в архиве." : removedTasks);
    }

    List<Task> getAllByDate(LocalDate date) {
        return taskMap.values().stream()
                .filter(task -> task.appearsIn(date))
                .toList();
    }

    List<Task> getAllTypeTask(Type type) {
        List<Task> list = taskMap.values().stream().filter(s -> Objects.equals(s.getType(), type)).toList();
        return list.isEmpty() ? null : list;
    }

    void allTask() {
        System.out.println(taskMap.values().isEmpty() ? "Ни одной задачи не было найдено." : taskMap.values());
    }
    void editTask(int numTask, String title, String description){
        taskMap.get(numTask).setTitle(title);
        taskMap.get(numTask).setDescription(description);
    }
}
