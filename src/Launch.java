import entities.Task;
import entities.Type;
import service.TaskService;
import tasks.DailyTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Launch {

    public static void main(String[] args) {

        TaskService taskService = new TaskService();
        Task task = new DailyTask("ada", Type.PERSONAL, "awdadw");
        Task task1 = new DailyTask("ada", Type.PERSONAL, "awdadw");

        taskService.addTask(task);
        taskService.addTask(task1);
        String text = "11.08.2024";

        LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(taskService.getAllByDate(localDate));


    }
}