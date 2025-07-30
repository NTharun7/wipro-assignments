package assignment;

import java.util.*;

public class Q44 {
    public static void main(String[] args) {
        List<MyEmployee> employees = new ArrayList<>();
        employees.add(new MyEmployee(1, "Alice", 50000));
        employees.add(new MyEmployee(2, "Bob", 75000));
        employees.add(new MyEmployee(3, "Charlie", 60000));

        // Sort by salary (descending)
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("Sorted by Salary (Descending):");
        for (MyEmployee e : employees) {
            System.out.println(e);
        }

        // Sort by name (alphabetically)
        employees.sort(Comparator.comparing(e -> e.name));
        System.out.println("\nSorted by Name (Alphabetically):");
        for (MyEmployee e : employees) {
            System.out.println(e);
        }
    }

    // Renamed to avoid conflict
    static class MyEmployee {
        int id;
        String name;
        double salary;

        public MyEmployee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return id + " - " + name + " - " + salary;
        }
    }
}
/*
 Sorted by Salary (Descending):
2 - Bob - 75000.0
3 - Charlie - 60000.0
1 - Alice - 50000.0

Sorted by Name (Alphabetically):
1 - Alice - 50000.0
2 - Bob - 75000.0
3 - Charlie - 60000.0

 */
