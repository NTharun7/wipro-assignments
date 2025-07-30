package assignment;

import java.util.*;

class Book {
    int bookId;
    String bookName;

    public Book(int id, String name) {
        this.bookId = id;
        this.bookName = name;
    }

    @Override
    public String toString() {
        return "BookID: " + bookId + ", Name: " + bookName;
    }
}

public class Q41 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(103, "Java"));
        books.add(new Book(101, "Python"));
        books.add(new Book(102, "C++"));

        System.out.println("Sorted by Name:");
        books.sort(Comparator.comparing(b -> b.bookName));
        books.forEach(System.out::println);

        System.out.println("\nSorted by ID:");
        books.sort(Comparator.comparingInt(b -> b.bookId));
        books.forEach(System.out::println);
    }
}

/*
 Sorted by Name:
BookID: 102, Name: C++
BookID: 103, Name: Java
BookID: 101, Name: Python

Sorted by ID:
BookID: 101, Name: Python
BookID: 102, Name: C++
BookID: 103, Name: Java

 */
