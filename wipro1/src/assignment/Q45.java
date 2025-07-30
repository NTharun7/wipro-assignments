package assignment;
import java.util.*; 
import java.util.stream.*; 
class Student { 
    int id; 
    String name; 
    String department; 
    public Student(int id, String name, String dept) { 
        this.id = id; 
        this.name = name; 
        this.department = dept; 
    } 
    public String toString() { 
        return name; 
    } 
} 

public class Q45 { 
    public static void main(String[] args) { 
        List<Student> students = Arrays.asList( 
            new Student(1, "kishan", "CSE"), 
            new Student(2, "Tej", "ECE"), 
            new Student(3, "suresh", "CSE"), 
            new Student(4, "abhi", "IT") 
        ); 
        Map<String, List<Student>> grouped = students.stream() 
            .collect(Collectors.groupingBy(s -> s.department)); 
        grouped.forEach((dept, list) -> { 
            System.out.println(dept + ": " + list); 
        }); 
    } 
}

/*
 CSE: [kishan, suresh]
ECE: [Tej]
IT: [abhi]

 
*/