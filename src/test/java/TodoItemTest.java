import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lex.model.Person;
import se.lex.model.TodoItem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {
    @Test
    void testTodoItemCreation() {
        Person person = new Person( "Rami", "Saloum");
        TodoItem todo = new TodoItem( "Shopping", "Milk, bread, eggs", LocalDate.now().plusDays(1), person);
        assertEquals(1, todo.getTodoItemId());
        assertEquals("Shopping", todo.getTitle());
        assertEquals("Milk, bread, eggs", todo.getDescription());
        assertEquals(person, todo.getAssignee());
    }


}
