import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lex.model.Person;
import se.lex.model.TodoItem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {
    private Person rami;
    private TodoItem todo;

    @BeforeEach
    void setUp() {
        rami = new Person("Rami Saloum", "rami@example.com");
        todo = new TodoItem("Buy groceries", "Milk, Bread, Eggs", LocalDate.of(2025, 10, 15), false, rami);
    }

    @Test
    void testTodoItemCreation() {
        assertNotNull(todo);
        assertEquals("Buy groceries", todo.getTitle());
        assertEquals(rami, todo.getPerson());
        assertFalse(todo.isDone());
    }

    @Test
    void testTodoItemDoneStatus() {
        todo.setDone(true);
        assertTrue(todo.isDone());
    }

    @Test
    void testInvalidTodoItemCreation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem("", "Description", LocalDate.now().minusDays(1), false, rami);
        });
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }
}
