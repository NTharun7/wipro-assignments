package assignment;

import java.util.function.IntPredicate;

public class Q28 {
    interface PerformOperation {
        boolean check(int a);
    }

    public static PerformOperation isOdd() {
        return a -> a % 2 != 0;
    }

    public static PerformOperation isPrime() {
        return a -> {
            if (a <= 1) return false;
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) return false;
            }
            return true;
        };
    }

    public static PerformOperation isPalindrome() {
        return a -> {
            String s = String.valueOf(a);
            return s.equals(new StringBuilder(s).reverse().toString());
        };
    }

    public static void main(String[] args) {
        System.out.println("isOdd(7): " + isOdd().check(7));
        System.out.println("isPrime(11): " + isPrime().check(11));
        System.out.println("isPalindrome(121): " + isPalindrome().check(121));
    }
}



/*
 isOdd(7): true
isPrime(11): true
isPalindrome(121): true

 */


