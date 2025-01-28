import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lex.model.Person;
import se.lex.model.TodoItem;
import se.lex.model.TodoItemTask;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {
    private Person rami;
    private TodoItem todo;
    private TodoItemTask task;

    @BeforeEach
    void setUp() {
        rami = new Person("Rami Saloum", "rami@example.com");
        todo = new TodoItem("Buy groceries", "Milk, Bread, Eggs", LocalDate.of(2025, 10, 15), false, rami);
        task = new TodoItemTask(todo, true, rami);
    }

    @Test
    void testTodoItemTaskCreation() {
        assertNotNull(task);
        assertEquals(todo, task.getTodoItem());
        assertTrue(task.isAssigned());
        assertEquals(rami, task.getAssignedPerson());
    }

    @Test
    void testTodoItemTaskAssignment() {
        TodoItemTask task2 = new TodoItemTask(todo, false, null);
        assertFalse(task2.isAssigned());
        assertNull(task2.getAssignedPerson());
    }
}
