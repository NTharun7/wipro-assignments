package assignment;

import java.util.*;

// Generic Repository class
class Repository<T, ID> {
    Map<ID, T> store = new HashMap<>();

    void save(ID id, T entity) {
        store.put(id, entity);
    }

    T findById(ID id) {
        return store.get(id);
    }

    List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    void deleteById(ID id) {
        store.remove(id);
    }
}


class EmployeeV2 {
    int id;
    String name;
    double salary;

    EmployeeV2(int id, String name, double sal) {
        this.id = id;
        this.name = name;
        this.salary = sal;
    }

    public String toString() {
        return id + " " + name + " " + salary;
    }
}

// Main class
public class Q46 {
    public static void main(String[] args) {
        Repository<EmployeeV2, Integer> repo = new Repository<>();
        repo.save(1, new EmployeeV2(1, "Rakesh", 50000));
        repo.save(2, new EmployeeV2(2, "Tej", 60000));
        repo.save(3, new EmployeeV2(3, "Kishore", 55000));

        System.out.println("All Employees:");
        repo.findAll().forEach(System.out::println);

        System.out.println("\nFind by ID 2:");
        System.out.println(repo.findById(2));

        repo.deleteById(1);
        System.out.println("\nAfter Deletion:");
        repo.findAll().forEach(System.out::println);
    }
}


/*
 All Employees:
1 Rakesh 50000.0
2 Tej 60000.0
3 Kishore 55000.0

Find by ID 2:
2 Tej 60000.0

After Deletion:
2 Tej 60000.0
3 Kishore 55000.0
*/
 