package assignment;

import java.util.*;
import java.util.stream.*;

class myEmployee {
    int id;
    String name;
    String department;
    double salary;

    myEmployee(int id, String name, String dept, double sal) {
        this.id = id;
        this.name = name;
        this.department = dept;
        this.salary = sal;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return id + ", " + name + ", " + department + ", ₹" + salary;
    }
}

public class Q50 {
    public static void main(String[] args) {
        List<myEmployee> employees = Arrays.asList(
            new myEmployee(101, "Ravi", "HR", 50000),
            new myEmployee(102, "Priya", "IT", 60000),
            new myEmployee(103, "Arun", "HR", 55000),
            new myEmployee(104, "Kavya", "IT", 70000),
            new myEmployee(105, "Divya", "Sales", 45000)
        );

        System.out.println("Q1. All employee names:");
        employees.stream().map(e -> e.name).forEach(System.out::println);

        System.out.println("\nQ2. Employees with salary > 55000:");
        employees.stream().filter(e -> e.salary > 55000).forEach(System.out::println);

        System.out.println("\nQ3. Count of HR employees:");
        long count = employees.stream().filter(e -> e.department.equals("HR")).count();
        System.out.println(count);

        System.out.println("\nQ4. Employees sorted by salary desc:");
        employees.stream().sorted(Comparator.comparingDouble(myEmployee::getSalary).reversed()).forEach(System.out::println);

        System.out.println("\nQ5. Highest paid employee:");
        employees.stream().max(Comparator.comparingDouble(myEmployee::getSalary)).ifPresent(System.out::println);

        System.out.println("\nQ6. Average salary:");
        double avg = employees.stream().mapToDouble(myEmployee::getSalary).average().orElse(0);
        System.out.println(avg);

        System.out.println("\nQ7. All names into a list:");
        List<String> names = employees.stream().map(e -> e.name).collect(Collectors.toList());
        System.out.println(names);

        System.out.println("\nQ8. Grouped by department:");
        Map<String, List<myEmployee>> grouped = employees.stream().collect(Collectors.groupingBy(e -> e.department));
        grouped.forEach((k, v) -> {
            System.out.println(k + ": ");
            v.forEach(System.out::println);
        });

        System.out.println("\nQ9. Total salary per department:");
        Map<String, Double> totalSal = employees.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.summingDouble(myEmployee::getSalary)));
        totalSal.forEach((k, v) -> System.out.println(k + ": ₹" + v));

        System.out.println("\nQ10. Names in IT dept sorted by salary:");
        employees.stream().filter(e -> e.department.equals("IT"))
            .sorted(Comparator.comparingDouble(myEmployee::getSalary))
            .map(e -> e.name).forEach(System.out::println);

        System.out.println("\nQ11. Any employee earns < 40000?");
        System.out.println(employees.stream().anyMatch(e -> e.salary < 40000));

        System.out.println("\nQ12. Comma-separated names:");
        String commaNames = employees.stream().map(e -> e.name).collect(Collectors.joining(", "));
        System.out.println(commaNames);

        System.out.println("\nQ13. Top 2 highest earners:");
        employees.stream().sorted(Comparator.comparingDouble(myEmployee::getSalary).reversed()).limit(2).forEach(System.out::println);

        System.out.println("\nQ14. Skip first 2 and print rest:");
        employees.stream().skip(2).forEach(System.out::println);

        System.out.println("\nQ15. First 3 employees' names:");
        employees.stream().limit(3).map(e -> e.name).forEach(System.out::println);

        System.out.println("\nQ16. Employee with min salary in HR:");
        employees.stream().filter(e -> e.department.equals("HR"))
            .min(Comparator.comparingDouble(myEmployee::getSalary))
            .ifPresent(System.out::println);

        System.out.println("\nQ17. Partition by salary > 55000:");
        Map<Boolean, List<myEmployee>> partitioned = employees.stream().collect(Collectors.partitioningBy(e -> e.salary > 55000));
        partitioned.forEach((k, v) -> {
            System.out.println((k ? "Above 55000" : "55000 or less") + ":");
            v.forEach(System.out::println);
        });

        System.out.println("\nQ18. Map<Department, AverageSalary>:");
        Map<String, Double> avgSal = employees.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.averagingDouble(myEmployee::getSalary)));
        avgSal.forEach((k, v) -> System.out.println(k + ": ₹" + v));

        System.out.println("\nQ19. Sorted by name then salary:");
        employees.stream().sorted(Comparator.comparing((myEmployee e) -> e.name).thenComparingDouble(myEmployee::getSalary)).forEach(System.out::println);

        System.out.println("\nQ20. Map<Id, Name>:");
        Map<Integer, String> idNameMap = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.name));
        System.out.println(idNameMap);

        System.out.println("\nChallenge 1: Names start with D and end with a:");
        employees.stream().filter(e -> e.name.startsWith("D") && e.name.endsWith("a")).forEach(System.out::println);

        System.out.println("\nChallenge 2: Departments with more than 1 employee:");
        grouped.entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(e -> System.out.println(e.getKey()));

        System.out.println("\nChallenge 3: Second highest salary:");
        employees.stream().map(e -> e.salary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().ifPresent(System.out::println);
    }
}


/*
 Q1. All employee names:
Ravi
Priya
Arun
Kavya
Divya

Q2. Employees with salary > 55000:
102, Priya, IT, ₹60000.0
104, Kavya, IT, ₹70000.0

Q3. Count of HR employees:
2

Q4. Employees sorted by salary desc:
104, Kavya, IT, ₹70000.0
102, Priya, IT, ₹60000.0
103, Arun, HR, ₹55000.0
101, Ravi, HR, ₹50000.0
105, Divya, Sales, ₹45000.0

Q5. Highest paid employee:
104, Kavya, IT, ₹70000.0

Q6. Average salary:
56000.0

Q7. All names into a list:
[Ravi, Priya, Arun, Kavya, Divya]

Q8. Grouped by department:
Sales: 
105, Divya, Sales, ₹45000.0
HR: 
101, Ravi, HR, ₹50000.0
103, Arun, HR, ₹55000.0
IT: 
102, Priya, IT, ₹60000.0
104, Kavya, IT, ₹70000.0

Q9. Total salary per department:
Sales: ₹45000.0
HR: ₹105000.0
IT: ₹130000.0

Q10. Names in IT dept sorted by salary:
Priya
Kavya

Q11. Any employee earns < 40000?
false

Q12. Comma-separated names:
Ravi, Priya, Arun, Kavya, Divya

Q13. Top 2 highest earners:
104, Kavya, IT, ₹70000.0
102, Priya, IT, ₹60000.0

Q14. Skip first 2 and print rest:
103, Arun, HR, ₹55000.0
104, Kavya, IT, ₹70000.0
105, Divya, Sales, ₹45000.0

Q15. First 3 employees' names:
Ravi
Priya
Arun

Q16. Employee with min salary in HR:
101, Ravi, HR, ₹50000.0

Q17. Partition by salary > 55000:
55000 or less:
101, Ravi, HR, ₹50000.0
103, Arun, HR, ₹55000.0
105, Divya, Sales, ₹45000.0
Above 55000:
102, Priya, IT, ₹60000.0
104, Kavya, IT, ₹70000.0

Q18. Map<Department, AverageSalary>:
Sales: ₹45000.0
HR: ₹52500.0
IT: ₹65000.0

Q19. Sorted by name then salary:
103, Arun, HR, ₹55000.0
105, Divya, Sales, ₹45000.0
104, Kavya, IT, ₹70000.0
102, Priya, IT, ₹60000.0
101, Ravi, HR, ₹50000.0

Q20. Map<Id, Name>:
{101=Ravi, 102=Priya, 103=Arun, 104=Kavya, 105=Divya}

Challenge 1: Names start with D and end with a:
105, Divya, Sales, ₹45000.0

Challenge 2: Departments with more than 1 employee:
HR
IT

Challenge 3: Second highest salary:
60000.0
*/
