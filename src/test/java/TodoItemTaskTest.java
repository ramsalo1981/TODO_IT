import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lex.model.Person;
import se.lex.model.TodoItem;
import se.lex.model.TodoItemTask;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {
    @Test
    void testTodoItemTaskCreation() {
        Person person = new Person( "Rami", "Saloum");
        TodoItem todo = new TodoItem( "Shopping", "Milk, bread, eggs", LocalDate.now().plusDays(1), person);
        TodoItemTask task = new TodoItemTask(todo, true, person);
        assertNotNull(task.getTaskId());
        assertEquals(todo, task.getTodoItem());
        assertEquals(person, task.getAssignedPerson());
        assertTrue(task.isAssigned());
    }






}
