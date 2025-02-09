import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lex.model.Person;
import se.lex.model.TodoItem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    void testPersonCreation() {
        Person person = new Person( "Rami", "Saloum");

        assertEquals("Rami", person.getFirstName());
        assertEquals("Saloum", person.getLastName());
    }




}
