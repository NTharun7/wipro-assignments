package junit.firstdemo;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

// ✅ Renamed model class from AccountServiceTest → Account
class Account {
    private int id;
    private double balance;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() { return id; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

interface AccountRepository {
    Account findById(int id);
    void update(Account account);
}

class AccountService {
    private AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public boolean transfer(int fromId, int toId, double amount) {
        Account from = repo.findById(fromId);
        Account to = repo.findById(toId);

        if (from.getBalance() < amount) return false;

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        repo.update(from);
        repo.update(to);
        return true;
    }
}

// ✅ This is your JUnit+Mockito test class
public class AccountServiceTest {

    @Mock
    private AccountRepository repo;

    @InjectMocks
    private AccountService service;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new AccountService(repo);
    }

    @Test
    public void testTransferSuccess() {
        Account acc1 = new Account(1, 1000);
        Account acc2 = new Account(2, 500);

        when(repo.findById(1)).thenReturn(acc1);
        when(repo.findById(2)).thenReturn(acc2);

        boolean result = service.transfer(1, 2, 300);

        assertTrue(result);
        assertEquals(700, acc1.getBalance(), 0.01);
        assertEquals(800, acc2.getBalance(), 0.01);
        verify(repo, times(1)).update(acc1);
        verify(repo, times(1)).update(acc2);
    }

    @Test
    public void testTransferFailsDueToLowBalance() {
        Account acc1 = new Account(1, 200);
        Account acc2 = new Account(2, 500);

        when(repo.findById(1)).thenReturn(acc1);
        when(repo.findById(2)).thenReturn(acc2);

        boolean result = service.transfer(1, 2, 300);

        assertFalse(result);
        verify(repo, never()).update(any());
    }
}
