package se.lex.menu;

import se.lex.dao.PersonDAO;
import se.lex.dao.PersonDAOCollection;
import se.lex.dao.TodoItemDAO;
import se.lex.dao.TodoItemDAOCollection;
import se.lex.model.Person;
import se.lex.model.TodoItem;
import se.lex.sequencer.TodoItemIdSequencer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class TodoItemMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TodoItemDAO todoItemDAO = TodoItemDAOCollection.getInstance();
    private static final PersonDAO personDAO = PersonDAOCollection.getInstance();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    public static void manageTodoItems() {

        while (true) {
            System.out.println("\n=== Todo Item Management ===");
            System.out.println("1. Create Todo Item");
            System.out.println("2. View All Todo Items");
            System.out.println("3. Find Item by ID");
            System.out.println("4. Find Items by Status");
            System.out.println("5. Find Items by Title");
            System.out.println("6. Find Items by Creator");
            System.out.println("7. Update Todo Item");
            System.out.println("8. Delete Todo Item");
            System.out.println("9. Return to Main Menu");
            System.out.print("Select option: ");

            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1: createTodoItem(); break;
                case 2: listAllTodoItems(); break;
                case 3: findTodoItemById(); break;
                case 4: findTodoItemsByStatus(); break;
                case 5: findTodoItemsByTitle(); break;
                case 6: findTodoItemsByCreator(); break;
                case 7: updateTodoItem(); break;
                case 8: deleteTodoItem(); break;
                case 9: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void createTodoItem() {
        int id = TodoItemIdSequencer.nextId();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        LocalDate deadline = getDateInput("Enter deadline (YYYY-MM-DD): ");
        if (deadline == null) return;

        System.out.print("Is done? (true/false): ");
        boolean done = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter creator ID: ");
        int creatorId = getIntInput();
        Person creator = personDAO.findById(creatorId);

        if (creator == null) {
            System.out.println("Creator not found!");
            return;
        }

        TodoItem newItem = new TodoItem(id, title, description, deadline, done, creator);
        todoItemDAO.persist(newItem);
        System.out.println("Todo Item created successfully!");
    }

    private static void listAllTodoItems() {
        Collection<TodoItem> items = todoItemDAO.findAll();
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        items.forEach(System.out::println);
    }

    private static void findTodoItemById() {
        System.out.print("Enter ID: ");
        int id = getIntInput();

        TodoItem item = todoItemDAO.findById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println(item);
    }

    private static void findTodoItemsByStatus() {
        System.out.print("Enter status (true/false): ");
        boolean done = scanner.nextBoolean();
        scanner.nextLine();

        Collection<TodoItem> items = todoItemDAO.findAllByDoneStatus(done);
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        items.forEach(System.out::println);
    }

    private static void findTodoItemsByTitle() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        Collection<TodoItem> items = todoItemDAO.findByTitleContains(title);
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        items.forEach(System.out::println);
    }

    private static void findTodoItemsByCreator() {
        System.out.print("Enter creator ID: ");
        int creatorId = getIntInput();

        Collection<TodoItem> items = todoItemDAO.findByPersonId(creatorId);
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        items.forEach(System.out::println);
    }

    private static void updateTodoItem() {
        System.out.print("Enter ID to update: ");
        int id = getIntInput();

        TodoItem item = todoItemDAO.findById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        LocalDate deadline = getDateInput("Enter new deadline (YYYY-MM-DD): ");
        if (deadline == null) return;

        System.out.print("Is done? (true/false): ");
        boolean done = scanner.nextBoolean();
        scanner.nextLine();

        item.setTitle(title);
        item.setDescription(description);
        item.setDeadLine(deadline);
        item.setDone(done);
        System.out.println("Item updated successfully!");
    }

    private static void deleteTodoItem() {
        System.out.print("Enter ID to delete: ");
        int id = getIntInput();

        todoItemDAO.remove(id);
        System.out.println("Item deleted successfully!");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static LocalDate getDateInput(String prompt) {
        System.out.print(prompt);
        try {
            return LocalDate.parse(scanner.nextLine(), dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Use YYYY-MM-DD.");
            return null;
        }
    }
}
