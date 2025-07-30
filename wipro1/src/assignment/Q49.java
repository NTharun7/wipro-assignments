package assignment;

import java.util.Optional;

class Employee49 {
    int id;
    String name;
    Optional<String> email;
    Optional<String> department;

    Employee49(int id, String name, Optional<String> email, Optional<String> department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }
}

class Q49 {
    public static void main(String[] args) {
        Employee49 emp1 = new Employee49(1, "Kiran", Optional.of("kiran@example.com"), Optional.empty());
        Employee49 emp2 = new Employee49(2, "Divya", Optional.empty(), Optional.of("Sales"));

        System.out.println("Employee: " + emp1.name);
        System.out.println("Email: " + emp1.email.orElse("Not Provided"));
        System.out.println("Department: " + emp1.department.orElse("Unknown"));

        System.out.println("\nEmployee: " + emp2.name);
        emp2.email.ifPresentOrElse(
            email -> System.out.println("Email: " + email),
            () -> System.out.println("Email is missing!")
        );

        try {
            String dept = emp2.department.orElseThrow(() -> new RuntimeException("Department not found"));
            System.out.println("Department: " + dept);
        } catch (RuntimeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
