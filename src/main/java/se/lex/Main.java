package se.lex;

import se.lex.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create AppUser instances
        AppUser user1 = new AppUser("Rami", "123", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("John", "456", AppRole.ROLE_APP_ADMIN);

        // Create Person instances
        Person person1 = new Person( "Rami", "Saloum");
        Person person2 = new Person( "John", "Hana");

        // Assign credentials to persons
        person1.setCredentials(user1);
        person2.setCredentials(user2);

        // Create TodoItem instances
        TodoItem todo1 = new TodoItem( "Shopping", "Milk, bread, eggs", LocalDate.now().plusDays(1), person1);
        TodoItem todo2 = new TodoItem( "Visit friend", "friend name: Marc", LocalDate.now().plusDays(3), person2);

        // Create TodoItemTask instances
        TodoItemTask task1 = new TodoItemTask(todo1, true, person1);
        TodoItemTask task2 = new TodoItemTask(todo2, true, person2);

        // Print details
        System.out.println("--- AppUser Details ---");
        System.out.println(user1);
        System.out.println(user2);

        System.out.println("\n--- Person Details ---");
        System.out.println(person1);
        System.out.println(person2);

        System.out.println("\n--- TodoItem Details ---");
        System.out.println(todo1);
        System.out.println(todo2);

        System.out.println("\n--- TodoItemTask Details ---");
        System.out.println(task1);
        System.out.println(task2);



    }
}