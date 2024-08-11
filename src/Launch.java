import entities.Task;
import entities.Type;
import service.ManageTask;
import service.TaskService;
import tasks.*;
import utils.TimeParse;

import java.time.LocalDate;


public static void main() {
    new ManageTask().startMenu();

    TaskService taskService = new TaskService();
    Task task = new YearlyTask("ada", Type.WORK, "awdadw");
    Task task1 = new DailyTask("ada", Type.PERSONAL, "awdadw");

    taskService.addTask(task);
    taskService.addTask(task1);

    String text = "11.08.2025";

    LocalDate localDate = TimeParse.parseDateTask(text);
    System.out.println(taskService.getAllByDate(localDate));


}