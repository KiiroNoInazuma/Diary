import entities.Task;
import entities.Type;
import service.TaskService;
import tasks.DailyTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Launch {

    public static void main(String[] args) {

        TaskService taskService = new TaskService();
        Task task = new DailyTask("ada", Type.PERSONAL, "awdadw");
        Task task1 = new DailyTask("ada", Type.PERSONAL, "awdadw");

        taskService.addTask(task);
        taskService.addTask(task1);
        String text = "11.08.2024 13:41";
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        System.out.println(taskService.getAllByDate(localDateTime));


    }
}