package se.lex.menu;

import se.lex.utils.SequencerUtils;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void printMenu() {
        SequencerUtils.loadSequencerValues();
        while (true) {
            System.out.println("\n=== Todo Application Main Menu ===");
            System.out.println("1. Manage App Users");
            System.out.println("2. Manage Persons");
            System.out.println("3. Manage Todo Items");
            System.out.println("4. Manage Todo Item Tasks");
            System.out.println("5. Exit");
            System.out.print("Select option: ");

            int choice = getIntInput();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1: AppUserMenu.manageAppUsers(); break;
                case 2: PersonMenu.managePersons(); break;
                case 3: TodoItemMenu.manageTodoItems(); break;
                case 4: TodoItemTasksMenu.manageTodoItemTasks(); break;
                case 5: exitApplication(); return;
                default: System.out.println("Invalid option!");
            }
        }

    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void exitApplication() {
        SequencerUtils.saveSequencerValues();
        System.out.println("Saving data...");
        System.out.println("Goodbye!");
    }
}
