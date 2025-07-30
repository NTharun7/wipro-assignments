package junit.firstdemo;

import static org.junit.Assert.*;
import org.junit.Test;

// Logic class
class LoginValidator {
    public static boolean isValidLogin(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return username.equals("admin") && password.equals("password123");
    }
}

// JUnit test class
public class LoginValidatorTest {

    @Test
    public void testValidLogin() {
        assertTrue(LoginValidator.isValidLogin("admin", "password123"));
    }

    @Test
    public void testInvalidLogin_WrongPassword() {
        assertFalse(LoginValidator.isValidLogin("admin", "wrongpass"));
    }

    @Test
    public void testInvalidLogin_WrongUsername() {
        assertFalse(LoginValidator.isValidLogin("user", "password123"));
    }

    @Test
    public void testInvalidLogin_EmptyUsername() {
        assertFalse(LoginValidator.isValidLogin("", "password123"));
    }

    @Test
    public void testInvalidLogin_EmptyPassword() {
        assertFalse(LoginValidator.isValidLogin("admin", ""));
    }

    @Test
    public void testInvalidLogin_NullUsername() {
        assertFalse(LoginValidator.isValidLogin(null, "password123"));
    }

    @Test
    public void testInvalidLogin_NullPassword() {
        assertFalse(LoginValidator.isValidLogin("admin", null));
    }
}
