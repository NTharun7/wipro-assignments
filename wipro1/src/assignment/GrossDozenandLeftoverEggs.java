package assignment;
	
import java.util.Scanner;

public class GrossDozenandLeftoverEggs {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter the number of eggs: ");
	        int eggs = scanner.nextInt();

	        int gross = eggs / 144;
	        int dozen = (eggs % 144) / 12;
	        int leftover = eggs % 12;

	        System.out.println("Your number of eggs is " + gross + " gross, " + dozen + " dozen, and " + leftover);
	        
	        scanner.close();
	    }
	}

	


