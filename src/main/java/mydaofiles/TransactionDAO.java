package mydaofiles;

import mypojos.Transaction;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class TransactionDAO {

    public void addTransaction(Transaction txn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(txn);
        tx.commit();
        session.close();
    }

    public List<Transaction> getAllTransactions() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> list = session.createQuery("from Transaction", Transaction.class).list();
        session.close();
        return list;
    }

    public Transaction getTransactionById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.get(Transaction.class, id);
        session.close();
        return txn;
    }

    public void updateTransaction(Transaction txn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(txn);
        tx.commit();
        session.close();
    }

    public void deleteTransaction(Transaction txn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(txn);
        tx.commit();
        session.close();
    }
}
