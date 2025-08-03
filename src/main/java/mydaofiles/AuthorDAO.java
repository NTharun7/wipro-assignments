package mydaofiles;

import mypojos.Author;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class AuthorDAO {

    public void addAuthor(Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(author);
        tx.commit();
        session.close();
    }

    public List<Author> getAllAuthors() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Author> list = session.createQuery("from Author", Author.class).list();
        session.close();
        return list;
    }

    public Author getAuthorById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public void deleteAuthor(Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(author);
        tx.commit();
        session.close();
    }
}
