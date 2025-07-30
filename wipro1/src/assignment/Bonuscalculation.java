package assignment;
import java.util.Scanner;

public class Bonuscalculation {
	


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();
        System.out.print("Enter years of service: ");
        int years = sc.nextInt();

        if (years > 6) {
            double bonus = salary * 0.10;
            System.out.println("Bonus is: " + bonus);
        } else {
            System.out.println("No bonus");
            
            sc.close();  // Only safe if you're done with all user input
}
    }
    

}



