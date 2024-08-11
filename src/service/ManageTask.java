package service;

import entities.Task;
import entities.Type;
import tasks.*;

import java.util.Scanner;

public final class ManageTask {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService;
    private boolean continueApp = true;

    public ManageTask() {
        this.taskService = new TaskService();
    }

    private void beautyEditor() {
        editText("ЕЖЕДНЕВНИК", 25);
        editText("   Меню", 25);
        editText(menu(), 12);
        System.out.print("Сделайте выбор: ");
        changeMenu(scanner.nextInt());

    }

    private void changeMenu(int num) {
        switch (num) {
            case 1 -> addTask();
            case 2 -> removeTask();
            case 4 -> taskService.allTask();
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
        System.out.print("Введите тип задачи (Рабочая/Личная): ");
        Type type = changeTypeTask(scanner.next());
        System.out.print("Опишите задачу: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("Установите период действия задачи <1 - однократная, 2- ежедневная, 3 - еженедельная, 4 - ежемесячная, 5 - ежегодная>");
        int num = scanner.nextInt();
        changeTaskPeriod(num, title, type, description);
    }


    private void removeTask() {
        System.out.print("Выберите id задачи, которую требуется удалить: ");
        int num = scanner.nextInt();
        Task remove = taskService.remove(num);
        System.out.printf("Задача -> %s <- удалена\n", remove);
    }

    private Type changeTypeTask(String type) {
        return type.equals("Рабочая") ? Type.WORK : Type.PERSONAL;
    }


    private String menu() {
        return "1 - Добавить задачу; 2 - Удалить задачу; 3 - Показать задачи на текущее число;  4 - Показать все задачи в ежедневнике.";
    }


    private void editText(String text, int x) {
        System.out.println("\t".repeat(x) + text);
    }

    private void entText() {
        System.out.print("===".repeat(75));
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
