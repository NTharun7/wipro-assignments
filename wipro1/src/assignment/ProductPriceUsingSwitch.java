package assignment;
import java.util.Scanner;
public class ProductPriceUsingSwitch {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double total = 0;
        char choice;

        do {
            System.out.print("Enter product number (1-3): ");
            int product = sc.nextInt();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            switch (product) {
                case 1:
                    total += 22.50 * qty;
                    break;
                case 2:
                    total += 44.50 * qty;
                    break;
                case 3:
                    total += 9.98 * qty;
                    break;
                default:
                    System.out.println("Invalid product number.");
            }

            System.out.print("Do you want to add another product? (Y/N): ");
            choice = sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        System.out.println("Total retail value: Rs. " + total);
        sc.close();
    }
}



