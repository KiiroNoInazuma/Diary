import service.ManageTask;
import utils.TimeParse;

import java.time.LocalDate;

private static final ManageTask manageTask = new ManageTask();

public static void main() {
    manageTask.startMenu();
    String text = "11.08.2025";
    LocalDate localDate = TimeParse.parseDateTask(text);
}