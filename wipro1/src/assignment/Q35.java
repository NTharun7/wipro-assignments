package assignment;

import java.util.Scanner;

class InvalidEmployeeCode extends Exception {
    public InvalidEmployeeCode(String message) {
        super(message);
    }
}

public class Q35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter employee code (format: YY-D-XXX): ");
        String code = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter year of birth: ");
        int year = sc.nextInt();

        try {
            validateCode(code);
            Employee emp = new Employee(code, name, year);
            emp.printDetails();
        } catch (InvalidEmployeeCode e) {
            System.out.println("❌ InvalidEmployeeCode: " + e.getMessage());
        }

        sc.close();
    }

    public static void validateCode(String code) throws InvalidEmployeeCode {
        if (!code.matches("\\d{2}-[FS]-\\d{3}")) {
            throw new InvalidEmployeeCode("Employee code format must be: YY-D-XXX (like 81-F-112)");
        }
    }
}


/*Enter employee code (format: YY-D-XXX): 05812
Enter name: suresh
Enter year of birth: 9122005
❌ InvalidEmployeeCode: Employee code format must be: YY-D-XXX (like 81-F-112)
*/
