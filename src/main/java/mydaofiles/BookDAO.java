package mydaofiles;

import mypojos.Book;
import mypojos.Author;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(book);
        tx.commit();
        session.close();
    }

    public List<Book> getAllBooks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Book> list = session.createQuery("from Book", Book.class).list();
        session.close();
        return list;
    }

    public Book getBookById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;
    }

    public void deleteBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(book);
        tx.commit();
        session.close();
    }

    public List<Author> getAllAuthors() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Author> query = session.createQuery("from Author", Author.class);
        List<Author> authors = query.list();
        session.close();
        return authors;
    }

    public Author getAuthorById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }
}
