package assignment;

// Custom checked exception
class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

// Renamed Employee class to avoid conflicts
class Employee47 {
    int id;
    String name;
    double salary;

    public Employee47(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee47 [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}

// EmployeeService class
class EmployeeService {
    public void validateSalary(double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative");
        }
    }

    public void processSalary(Employee47 emp) throws InvalidSalaryException {
        validateSalary(emp.salary);
        System.out.println("Salary is valid: " + emp.salary);
    }

    public void startProcess(Employee47 emp) throws InvalidSalaryException {
        processSalary(emp);
    }
}

// Main class
public class Q47 {
    public static void main(String[] args) {
        Employee47 emp = new Employee47(1, "Ravi", -40000);
        EmployeeService service = new EmployeeService();

        try {
            service.startProcess(emp);
        } catch (InvalidSalaryException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
/*
Exception caught: Salary cannot be negative
 */
