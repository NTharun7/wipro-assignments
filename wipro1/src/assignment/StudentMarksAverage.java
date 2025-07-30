package assignment;

import java.util.Scanner;

public class StudentMarksAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mark;
        int total = 0;
        int count = 0;

        while (count < 3) {
            System.out.print("Enter the mark (0-100) for student " + (count + 1) + ": ");
            mark = scanner.nextInt();

            if (mark >= 0 && mark <= 100) {
                total += mark;
                count++;
            } else {
                System.out.println("Invalid input, try again...");
            }
        }

        double average = (double) total / 3;
        System.out.printf("The average is: %.2f\n", average);

        scanner.close();
    }
}
