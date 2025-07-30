package assignment;

import java.util.Scanner;

public class Q31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter an integer: ");
            int num = sc.nextInt();
            sc.nextLine();  // clear the leftover newline

            System.out.print("Enter a word: ");
            String word = sc.nextLine();  // read the full line

            // Try to print the character at index 10
            System.out.println("Character at index 10: " + word.charAt(10));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("❌ Caught StringIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Some other error occurred: " + e.getMessage());
        }
    }
}


/*Enter an integer: 5
Enter a word: hello
❌ Caught StringIndexOutOfBoundsException: Index 10 out of bounds for length 5
*/