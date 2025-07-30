package assignment;

@FunctionalInterface
interface EmployeeProcessor {
    void process(Employee48 e);
}

class Employee48 {
    int id;
    String name;
    double salary;

    Employee48(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

class Q48 {
    public static void main(String[] args) {
        Employee48 emp = new Employee48(1, "Priya", 60000);

        EmployeeProcessor printDetails = e -> System.out.println("Name: " + e.name + ", Salary: " + e.salary);
        EmployeeProcessor calculateBonus = e -> System.out.println("Bonus: " + (e.salary * 0.10));

        printDetails.process(emp);
        calculateBonus.process(emp);
    }
}