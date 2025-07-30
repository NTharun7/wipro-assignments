package assignment;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Q29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String regNo = sc.nextLine();
        String mobile = sc.nextLine();

        try {
            if (regNo.length() != 9)
                throw new IllegalArgumentException("Invalid Register Number length");

            if (!regNo.matches("[A-Za-z0-9]+"))
                throw new NoSuchElementException("Invalid character in Register Number");

            if (mobile.length() != 10)
                throw new IllegalArgumentException("Invalid Mobile Number length");

            if (!mobile.matches("\\d+"))
                throw new NumberFormatException("Mobile number contains non-digit");

            System.out.println("valid");
        } catch (Exception e) {
            System.out.println("invalid");
           
        }
    }
}


/*
ABC123XYZ
9876543210
valid
*/