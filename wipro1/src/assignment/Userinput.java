package assignment;
import java.util.Scanner;

public class Userinput {

  public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        // Taking user input
	        System.out.print("Enter your name: ");
	        String name = sc.nextLine();

	        System.out.print("Enter your roll number: ");
	        String roll = sc.nextLine();

	        System.out.print("Enter your field of interest: ");
	        String interest = sc.nextLine();

	        // Printing formatted output
	        System.out.println("Hey, my name is " + name + " and my roll number is " + roll +
	                           ". My field of interest is " + interest + ".");
	    }
	}


	

