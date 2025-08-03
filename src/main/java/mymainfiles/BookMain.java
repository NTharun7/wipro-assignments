package mymainfiles;

import mydaofiles.BookDAO;
import mydaofiles.AuthorDAO;
import mypojos.Book;
import mypojos.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookMain {
    static Scanner sc = new Scanner(System.in);
    static BookDAO bookDAO = new BookDAO();
    static AuthorDAO authorDAO = new AuthorDAO();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n----- Book & Author Menu -----");
            System.out.println("1. Add Book with Authors");
            System.out.println("2. View All Books with Authors");
            System.out.println("3. Delete Book");
            System.out.println("4. Add Author");
            System.out.println("5. View All Authors");
            System.out.println("6. Delete Author");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBookWithAuthors();
                case 2 -> viewAllBooks();
                case 3 -> deleteBook();
                case 4 -> addAuthor();
                case 5 -> viewAllAuthors();
                case 6 -> deleteAuthor();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addBookWithAuthors() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        Book book = new Book(title);

        List<Author> selectedAuthors = new ArrayList<>();
        viewAllAuthors();

        System.out.print("How many authors to add to this book? ");
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.print("Enter Author ID: ");
            int authorId = sc.nextInt();
            Author author = authorDAO.getAuthorById(authorId);
            if (author != null) {
                selectedAuthors.add(author);
            } else {
                System.out.println("Author ID not found.");
            }
        }

        book.setAuthors(selectedAuthors);
        bookDAO.addBook(book);
        System.out.println("Book with authors added.");
    }

    private static void viewAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book b : books) {
            System.out.println(b);
            if (b.getAuthors() != null) {
                for (Author a : b.getAuthors()) {
                    System.out.println("   -> " + a);
                }
            }
        }
    }

    private static void deleteBook() {
        viewAllBooks();
        System.out.print("Enter book ID to delete: ");
        int id = sc.nextInt();
        Book book = bookDAO.getBookById(id);
        if (book != null) {
            bookDAO.deleteBook(book);
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void addAuthor() {
        System.out.print("Enter author name: ");
        String name = sc.nextLine();
        Author author = new Author(name);
        authorDAO.addAuthor(author);
        System.out.println("Author added.");
    }

    private static void viewAllAuthors() {
        List<Author> authors = authorDAO.getAllAuthors();
        for (Author a : authors) {
            System.out.println(a);
        }
    }

    private static void deleteAuthor() {
        viewAllAuthors();
        System.out.print("Enter author ID to delete: ");
        int id = sc.nextInt();
        Author author = authorDAO.getAuthorById(id);
        if (author != null) {
            authorDAO.deleteAuthor(author);
            System.out.println("Author deleted.");
        } else {
            System.out.println("Author not found.");
        }
    }
}
