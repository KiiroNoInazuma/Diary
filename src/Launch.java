import entities.Task;
import entities.Type;
import service.ManageTask;
import service.TaskService;
import tasks.*;
import utils.TimeParse;

import java.time.LocalDate;


public static void main() {


    TaskService taskService = new TaskService();



    String text = "11.08.2025";

    LocalDate localDate = TimeParse.parseDateTask(text);
    new ManageTask().startMenu();


}