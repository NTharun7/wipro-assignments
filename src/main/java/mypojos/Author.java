package mypojos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private List<Book> books;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    // Getters & Setters

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Book> getBooks() { return books; }

    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + "]";
    }
}
