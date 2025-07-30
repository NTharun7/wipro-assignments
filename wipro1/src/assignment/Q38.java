package assignment;

import java.io.*;
import java.util.Scanner;

public class Q38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter employee name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        Employee emp = new Employee(id, name, salary);

        // Serialize the object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            oos.writeObject(emp);
            System.out.println("✅ Employee object serialized (salary not saved).");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.ser"))) {
            Employee deserializedEmp = (Employee) ois.readObject();
            System.out.println("✅ Deserialized Employee:");
            System.out.println(deserializedEmp);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    // Inner class
    static class Employee implements Serializable {
        private int emp_id;
        private String emp_name;
        private transient double emp_sal; // salary will not be serialized

        public Employee(int emp_id, String emp_name, double emp_sal) {
            this.emp_id = emp_id;
            this.emp_name = emp_name;
            this.emp_sal = emp_sal;
        }

        @Override
        public String toString() {
            return "Employee ID: " + emp_id + ", Name: " + emp_name + ", Salary: " + emp_sal;
        }
    }
}

/*Enter employee ID: 12
Enter employee name: vinay
Enter employee salary: 25000
✅ Employee object serialized (salary not saved).
✅ Deserialized Employee:
Employee ID: 12, Name: vinay, Salary: 0.0
*/
