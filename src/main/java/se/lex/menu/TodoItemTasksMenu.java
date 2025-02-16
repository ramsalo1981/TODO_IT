package se.lex.menu;

import se.lex.dao.*;
import se.lex.model.Person;
import se.lex.model.TodoItem;
import se.lex.model.TodoItemTask;
import se.lex.sequencer.TodoItemTaskIdSequencer;

import java.util.Collection;
import java.util.Scanner;

public class TodoItemTasksMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TodoItemDAO todoItemDAO = TodoItemDAOCollection.getInstance();
    private static final PersonDAO personDAO = PersonDAOCollection.getInstance();
    private static final TodoItemTaskDAO todoItemTaskDAO = TodoItemTaskDAOCollection.getInstance();
    public static void manageTodoItemTasks() {
        while (true) {
            System.out.println("\n=== Todo Item Task Management ===");
            System.out.println("1. Create Task Assignment");
            System.out.println("2. View All Tasks");
            System.out.println("3. Find Task by ID");
            System.out.println("4. Find Tasks by Status");
            System.out.println("5. Find Tasks by Assignee");
            System.out.println("6. Update Task Assignment");
            System.out.println("7. Delete Task Assignment");
            System.out.println("8. Return to Main Menu");
            System.out.print("Select option: ");

            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1: createTodoItemTask(); break;
                case 2: listAllTodoItemTasks(); break;
                case 3: findTodoItemTaskById(); break;
                case 4: findTodoItemTasksByStatus(); break;
                case 5: findTodoItemTasksByAssignee(); break;
                case 6: updateTodoItemTask(); break;
                case 7: deleteTodoItemTask(); break;
                case 8: return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void createTodoItemTask() {
        int id = TodoItemTaskIdSequencer.nextId();

        System.out.print("Enter Todo Item ID: ");
        int itemId = getIntInput();
        TodoItem todoItem = todoItemDAO.findById(itemId);

        if (todoItem == null) {
            System.out.println("Todo Item not found!");
            return;
        }

        System.out.print("Enter Assignee ID (0 for unassigned): ");
        int assigneeId = getIntInput();
        Person assignee = assigneeId == 0 ? null : personDAO.findById(assigneeId);

        if (assigneeId != 0 && assignee == null) {
            System.out.println("Assignee not found!");
            return;
        }

        TodoItemTask newTask = new TodoItemTask(id, todoItem, assignee);
        todoItemTaskDAO.persist(newTask);
        System.out.println("Task assignment created successfully!");
    }

    private static void listAllTodoItemTasks() {
        Collection<TodoItemTask> tasks = todoItemTaskDAO.findAll();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        tasks.forEach(System.out::println);
    }

    private static void findTodoItemTaskById() {
        System.out.print("Enter ID: ");
        int id = getIntInput();

        TodoItemTask task = todoItemTaskDAO.findById(id);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }
        System.out.println(task);
    }

    private static void findTodoItemTasksByStatus() {
        System.out.print("Enter status (true/false): ");
        boolean assigned = scanner.nextBoolean();
        scanner.nextLine();

        Collection<TodoItemTask> tasks = todoItemTaskDAO.findByAssignedStatus(assigned);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        tasks.forEach(System.out::println);
    }

    private static void findTodoItemTasksByAssignee() {
        System.out.print("Enter assignee ID: ");
        int assigneeId = getIntInput();

        Collection<TodoItemTask> tasks = todoItemTaskDAO.findByPersonId(assigneeId);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        tasks.forEach(System.out::println);
    }

    private static void updateTodoItemTask() {
        System.out.print("Enter ID to update: ");
        int id = getIntInput();

        TodoItemTask task = todoItemTaskDAO.findById(id);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.print("Enter new Todo Item ID: ");
        int itemId = getIntInput();
        TodoItem todoItem = todoItemDAO.findById(itemId);

        if (todoItem == null) {
            System.out.println("Todo Item not found!");
            return;
        }

        System.out.print("Enter new Assignee ID (0 for unassigned): ");
        int assigneeId = getIntInput();
        Person assignee = assigneeId == 0 ? null : personDAO.findById(assigneeId);

        if (assigneeId != 0 && assignee == null) {
            System.out.println("Assignee not found!");
            return;
        }

        task.setTodoItem(todoItem);
        task.setAssignee(assignee);
        System.out.println("Task updated successfully!");
    }

    private static void deleteTodoItemTask() {
        System.out.print("Enter ID to delete: ");
        int id = getIntInput();

        todoItemTaskDAO.remove(id);
        System.out.println("Task deleted successfully!");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
