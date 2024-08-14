package service;

import entities.Task;
import entities.Type;
import exceptions.TaskNotFoundException;
import tasks.*;
import utils.TimeParse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class ManageTask {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService;
    private boolean continueApp = true;

    public ManageTask() {
        this.taskService = new TaskService();
    }

    private void beautyEditor() {
        boolean check = false;
        editText("ЕЖЕДНЕВНИК", 25);
        while (!check) {
            editText("   Меню", 25);
            editText(menu(), 10);
            System.out.print("Сделайте выбор: ");
            try {
                changeMenu(scanner.nextInt());
                check = true;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод, попробуйте еще раз");
                scanner.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты, введите дату в формате \"dd.MM.yyyy\"");
            } catch (RuntimeException e) {
                System.out.println("Такой задачи не существует");
                scanner.nextLine();
            }
        }
    }

    private void changeMenu(int num) {
        switch (num) {
            case 1 -> addTask();
            case 2 -> removeTask();
            case 3 -> allRemovedTasks();
            case 4 -> getAllByDate();
            case 5 -> getAllTypeTask();
            case 6 -> taskService.allTask();
            case 7 -> editTask();
            default -> {
                System.out.println("Неверный выбор. Пожалуйста, попробуйте снова");
                beautyEditor();
            }
        }
    }

    private void changeTaskPeriod(int num, String title, Type type, String description) {
        switch (num) {
            case 1 -> taskService.addTask(new OneTimeTask(title, type, description));
            case 2 -> taskService.addTask(new DailyTask(title, type, description));
            case 3 -> taskService.addTask(new WeeklyTask(title, type, description));
            case 4 -> taskService.addTask(new MonthlyTask(title, type, description));
            case 5 -> taskService.addTask(new YearlyTask(title, type, description));
            default -> {
                System.out.println("Неверный выбор. Пожалуйста, попробуйте снова");
                beautyEditor();
            }
        }
    }


    private void addTask() {
        System.out.print("Введите название задачи: ");
        String title = scanner.next();
        System.out.print("Введите тип задачи (рабочая/личная): ");
        Type type = changeTypeTask(scanner.next());
        if (Objects.isNull(type)) {
            System.out.println("Такого типа не существует, повторите ввод");
            addTask();
            return;
        }
        System.out.print("Опишите задачу: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("Установите период действия задачи <1 - однократная, 2 - ежедневная, 3 - еженедельная, 4 - ежемесячная, 5 - ежегодная>");
        int num = scanner.nextInt();
        changeTaskPeriod(num, title, type, description);
        System.out.println("Задача успешно записана.");
    }

    private void editTask() {
        System.out.print("Введите id задачи для редактирования: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите новый заголовок: ");
        String title = scanner.nextLine();
        System.out.print("Введите новое описание: ");
        String description = scanner.nextLine();
        taskService.editTask(id, title, description);
        try {
            fixEnt();
        } catch (AWTException e) {
            System.out.println("Ошибка");
        }

    }

    void fixEnt() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void removeTask() {
        System.out.print("Выберите id задачи, которую требуется удалить: ");
        int num = scanner.nextInt();
        Task remove = taskService.remove(num);
        if (Objects.isNull(remove)) {
            throw new TaskNotFoundException("Такой задачи не существует, удалять нечего");
        }
        System.out.printf("Задача -> %s <- удалена\n", remove);
    }

    private void allRemovedTasks() {
        taskService.allRemovedTasks();
    }

    private void getAllByDate() {
        System.out.print("Введите дату задачи: ");
        String date = scanner.next();
        LocalDate localDate = TimeParse.parseDateTask(date);
        List<Task> allByDate = taskService.getAllByDate(localDate);
        System.out.println(allByDate.isEmpty() ? "На текущую дату задача не найдена" : allByDate);
    }

    private void getAllTypeTask() {
        System.out.print("Выберите тип задачи (рабочая/личная): ");
        String typeTask = scanner.next();
        if (typeTask.equalsIgnoreCase("рабочая")) {
            System.out.println(Objects.requireNonNull(taskService.getAllTypeTask(Type.WORK)));
        } else if (typeTask.equalsIgnoreCase("личная")) {
            System.out.println(Objects.requireNonNull(taskService.getAllTypeTask(Type.PERSONAL)));
        } else {
            System.out.println("Ни одной задачи не найдено");
        }
    }

    private Type changeTypeTask(String type) {
        if (type.equalsIgnoreCase("рабочая")) {
            return Type.WORK;
        } else if (type.equalsIgnoreCase("личная")) {
            return Type.PERSONAL;
        }
        return null;
    }


    private String menu() {
        return """
                                                         1 - Добавить задачу;     4 - Показать задачи на текущее число;
                                                             \t\t\t\t\t\t\t\t\t 2 - Удалить задачу;      5 - Показать задачи по типу;
                                                         \t\t\t\t\t\t\t\t\t     3 - Архив задач;         6 - Показать все задачи в ежедневнике;
                                                                    \t\t\t\t\t\t\t\t\t     7 - Редактировать задачу.
                """;
    }


    private void editText(String text, int x) {
        System.out.println("\t".repeat(x) + text);
    }

    private void entText() {
        System.out.print("===".repeat(73));
        System.out.println("\n".repeat(1));
    }

    public void startMenu() {
        System.out.println();
        while (continueApp) {
            beautyEditor();
            System.out.println("Если вы хотите выйти из программы введите \"exit\", если хотите продолжить, нажмите \"Enter\"");
            scanner.nextLine();
            if (scanner.nextLine().equalsIgnoreCase("exit")) continueApp = false;
            entText();
        }
        scanner.close();

    }
}
