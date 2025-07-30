package junit.firstdemo;


import org.junit.Test;
import static org.junit.Assert.*;

public class FactorialTest {

    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Invalid");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    @Test
    public void testFactorialOf5() {
        assertEquals(120, factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInput() {
        factorial(-1);
    }
}
