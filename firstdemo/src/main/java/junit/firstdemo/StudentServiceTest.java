package junit.firstdemo;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// Dummy Student class
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }
}

// Repository interface
interface StudentRepository {
    Student findById(int id);
}

// Service that uses the repository
class StudentService {
    private StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student getStudentById(int id) {
        return repo.findById(id);
    }
}

// Test class using Mockito
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void testGetStudentById() {
        Student dummyStudent = new Student(1, "John");
        when(studentRepository.findById(1)).thenReturn(dummyStudent);

        Student result = studentService.getStudentById(1);

        assertEquals("John", result.getName());
        verify(studentRepository, times(1)).findById(1);
    }
}
