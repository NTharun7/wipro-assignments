package mypojos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book() {}

    public Book(String title) {
        this.title = title;
    }

    // Getters & Setters

    public int getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<Author> getAuthors() { return authors; }

    public void setAuthors(List<Author> authors) { this.authors = authors; }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + "]";
    }
}
	