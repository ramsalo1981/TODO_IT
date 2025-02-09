import org.junit.jupiter.api.Test;
import se.lex.model.AppRole;
import se.lex.model.AppUser;

import static org.junit.jupiter.api.Assertions.*;
public class AppUserTest {
    @Test
    void testAppUserCreation() {
        AppUser user = new AppUser("Rami", "123", AppRole.ROLE_APP_USER);
        assertEquals("Rami", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    void testAppUserEqualsAndHashCode() {
        AppUser user1 = new AppUser("Rami", "123", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("Rami", "123", AppRole.ROLE_APP_USER);
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testAppUserToString() {
        AppUser user = new AppUser("Rami", "123", AppRole.ROLE_APP_USER);
        String expected = "AppUser{username='Rami', role=ROLE_APP_USER}";
        assertEquals(expected, user.toString());
    }

    @Test
    void testAppUserValidation() {
        assertThrows(IllegalArgumentException.class, () -> new AppUser("", "123", AppRole.ROLE_APP_USER));
        assertThrows(IllegalArgumentException.class, () -> new AppUser("Rami", "", AppRole.ROLE_APP_USER));
        assertThrows(IllegalArgumentException.class, () -> new AppUser("Rami", "123", null));
    }
}
