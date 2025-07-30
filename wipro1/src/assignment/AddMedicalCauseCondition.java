package assignment;

import java.util.Scanner;

public class AddMedicalCauseCondition {
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of classes held: ");
        int held = sc.nextInt();

        System.out.print("Enter number of classes attended: ");
        int attended = sc.nextInt();

        double percent = (attended * 100.0) / held;
        System.out.println("Attendance Percentage: " + percent + "%");

        if (percent >= 70) {
            System.out.println("Allowed to sit in exam.");
        } else {
            System.out.print("Do you have a medical cause? (Y/N): ");
            char cause = sc.next().charAt(0);

            if (cause == 'Y' || cause == 'y')
                System.out.println("Allowed to sit in exam due to medical reason.");
            else
                System.out.println("Not allowed to sit in exam.");
        }
    }

}
