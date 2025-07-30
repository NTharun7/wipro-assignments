package junit.firstdemo;

import static org.junit.Assert.*;
import org.junit.Test;

// Logic class
class ReverseHelper {
    public static String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }
}

// JUnit test class
public class ReverseHelperTest {

    @Test
    public void testReverseRegularString() {
        assertEquals("dcba", ReverseHelper.reverse("abcd"));
    }

    @Test
    public void testReverseEmptyString() {
        assertEquals("", ReverseHelper.reverse(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverseNullString() {
        ReverseHelper.reverse(null);
    }
}
