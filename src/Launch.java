import entities.Task;
import entities.Type;
import service.TaskService;
import tasks.*;
import utils.TimeParse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Launch {

    public static void main(String[] args) {

        TaskService taskService = new TaskService();
        Task task = new YearlyTask("ada", Type.WORK, "awdadw");
        Task task1 = new DailyTask("ada", Type.PERSONAL, "awdadw");

        taskService.addTask(task);
        taskService.addTask(task1);

        String text = "11.08.2025";

        LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern(TimeParse.defaultPattern));

        taskService.getAllTypeTask(Type.WORK);


    }
}