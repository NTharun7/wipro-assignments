package junit.firstdemo;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void testReverseWithNull() {
        assertNull(StringUtil.reverse(null));
    }

    @Test
    public void testReverseWithEmptyString() {
        assertEquals("", StringUtil.reverse(""));
    }

    @Test
    public void testReverseWithRegularString() {
        assertEquals("tac", StringUtil.reverse("cat"));
        assertEquals("avaJ", StringUtil.reverse("Java"));
    }
}
