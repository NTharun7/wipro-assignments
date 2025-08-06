package com.wiproassignments.springbootcontrollerfiles;

import com.wiproassignments.springbootcontrollerfiles.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    @GetMapping(value = "/bookXYZ", produces = "application/xml")
    public List<Book> getBooks() {
        return Arrays.asList(
            new Book(101, "Java Tutorials", "Krishna"),
            new Book(102, "Spring Tutorials", "Mahesh"),
            new Book(103, "Angular Tutorials", "Shiva")
        );
    }
}
