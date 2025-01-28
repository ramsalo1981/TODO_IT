import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lex.model.Person;
import se.lex.model.TodoItem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person rami;

    @BeforeEach
    void setUp() {
        rami = new Person("Rami Saloum", "rami@example.com");
    }
    @Test
    void testPersonCreation() {
        assertNotNull(rami);
        assertEquals("Rami Saloum", rami.getName());
        assertEquals("rami@example.com", rami.getEmail());
    }

    @Test
    void testAddTodoItem() {
        TodoItem todo1 = new TodoItem("Buy groceries", "Milk, Bread, Eggs", LocalDate.of(2025, 10, 15), false, rami);
        assertEquals(1, rami.getTodoItems().size());

        TodoItem todo2 = new TodoItem("Finish project report", "Complete the final report for the project", LocalDate.of(2025, 10, 20), false, rami);
        assertEquals(2, rami.getTodoItems().size());
    }

    @Test
    void testInvalidPersonCreation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("", "invalid-email");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
}
