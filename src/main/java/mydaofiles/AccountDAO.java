package mydaofiles;

import mypojos.Account;
import mypojos.Transaction;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class AccountDAO {

    public void addAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(account);
        tx.commit();
        session.close();
    }

    public List<Account> getAllAccounts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> list = session.createQuery("from Account", Account.class).list();
        session.close();
        return list;
    }

    public Account getAccountById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Account acc = session.get(Account.class, id);
        session.close();
        return acc;
    }

    public void updateAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(account);
        tx.commit();
        session.close();
    }

    public void deleteAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(account);
        tx.commit();
        session.close();
    }

    public void addTransactionToAccount(Transaction txn, int accountId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        Account acc = session.get(Account.class, accountId);
        if (acc != null) {
            txn.setAccount(acc);
            session.save(txn);
        }

        tx.commit();
        session.close();
    }

    public List<Transaction> getTransactionsForAccount(int accountId) {
        Account account = getAccountById(accountId);
        if (account != null) {
            return account.getTransactions();
        }
        return null;
    }
}
