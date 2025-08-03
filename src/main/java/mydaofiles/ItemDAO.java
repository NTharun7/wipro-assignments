package mydaofiles;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mypojos.Item;
import util.HibernateUtil;

public class ItemDAO {

    public void addItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(item);
        tx.commit();
        session.close();
    }

    public List<Item> getAllItems() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Item> list = session.createQuery("from Item", Item.class).list();
        session.close();
        return list;
    }

    public Item getItemById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Item item = session.get(Item.class, id);
        session.close();
        return item;
    }

    public void updateItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(item);
        tx.commit();
        session.close();
    }

    public void deleteItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(item);
        tx.commit();
        session.close();
    }
}
