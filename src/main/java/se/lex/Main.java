package se.lex;

import se.lex.model.Person;
import se.lex.model.TodoItem;
import se.lex.model.TodoItemTask;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create some Person instances
        Person rami = new Person("Rami Saloum", "rami@example.com");
        Person ann = new Person("ann sofi", "ann@example.com");

        // Create some TodoItem instances
        TodoItem todo1 = new TodoItem("Buy groceries", "Milk, Bread, Eggs", LocalDate.of(2025, 10, 15), false, rami);
        TodoItem todo2 = new TodoItem("Finish project report", "Complete the final report for the project", LocalDate.of(2025, 10, 20), false, rami);
        TodoItem todo3 = new TodoItem("Call the plumber", "Fix the leaking sink", LocalDate.of(2025, 10, 18), false, ann);

        // Create TodoItemTask instances
        TodoItemTask task1 = new TodoItemTask(todo1, true, rami);
        TodoItemTask task2 = new TodoItemTask(todo2, false, ann);
        TodoItemTask task3 = new TodoItemTask(todo3, true, ann);


//        System.out.println(alice.toString());
//        System.out.println(bob.toString());
//
//        // Print out the details of each TodoItemTask
//        System.out.println(task1.toString());
//        System.out.println(task2.toString());
//        System.out.println(task3.toString());

        // Mark a TodoItem as done
        todo1.setDone(true);
        System.out.println("After completing the task:");
        System.out.println(task1);
        System.out.println(rami.toString());

    }
}