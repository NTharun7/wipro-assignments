package assignment;

import java.util.*;

public class Q26 {
    public static void main(String[] args) {
        int[] input = {2, 3, 54, 1, 6, 7, 7};

        Set<Integer> unique = new HashSet<>();
        int evenSum = 0;

        for (int num : input) {
            if (unique.add(num) && num % 2 == 0) {
                evenSum += num;
            }
        }

        System.out.println("Sum of even numbers (no duplicates): " + evenSum);
    }
}




//Sum of even numbers (no duplicates): 62
