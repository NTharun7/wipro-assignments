package assignment;

public class Q34 {
    public static void main(String[] args) {
        Employee emp = new Employee("81-F-112", "John Doe", 1981);
        emp.printDetails();
    }
}

class Employee {
    String empId;
    String name;
    int yearOfBirth;

    public Employee(String empId, String name, int yearOfBirth) {
        this.empId = empId;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public void printDetails() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Year of Birth: " + yearOfBirth);
    }
}
