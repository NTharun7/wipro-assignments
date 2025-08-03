package mymainfiles;

import mydaofiles.AccountDAO;
import mypojos.Account;
import mypojos.Transaction;

import java.util.List;
import java.util.Scanner;

public class AccountMain {
    static Scanner sc = new Scanner(System.in);
    static AccountDAO dao = new AccountDAO();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. View Transactions of Account");
            System.out.println("6. Add Transaction to Account");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addAccount();
                case 2 -> viewAllAccounts();
                case 3 -> updateAccount();
                case 4 -> deleteAccount();
                case 5 -> viewTransactions();
                case 6 -> addTransaction();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addAccount() {
        System.out.print("Enter account description: ");
        String desc = sc.nextLine();
        Account acc = new Account(desc);
        dao.addAccount(acc);
        System.out.println("Account added.");
    }

    private static void viewAllAccounts() {
        List<Account> accounts = dao.getAllAccounts();
        for (Account a : accounts) {
            System.out.println(a);
        }
    }

    private static void updateAccount() {
        System.out.print("Enter account ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Account acc = dao.getAccountById(id);
        if (acc != null) {
            System.out.print("Enter new description: ");
            acc.setDescription(sc.nextLine());
            dao.updateAccount(acc);
            System.out.println("Account updated.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter account ID to delete: ");
        int id = sc.nextInt();

        Account acc = dao.getAccountById(id);
        if (acc != null) {
            dao.deleteAccount(acc);
            System.out.println("Account deleted.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewTransactions() {
        System.out.print("Enter account ID to view transactions: ");
        int id = sc.nextInt();
        List<Transaction> txns = dao.getTransactionsForAccount(id);
        if (txns != null && !txns.isEmpty()) {
            for (Transaction t : txns) {
                System.out.println(t);
            }
        } else {
            System.out.println("No transactions found or account doesn't exist.");
        }
    }

    private static void addTransaction() {
        System.out.print("Enter account ID to add transaction: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter transaction details: ");
        String details = sc.nextLine();

        System.out.print("Enter transaction amount: ");
        double amount = sc.nextDouble();

        Transaction t = new Transaction(details, amount, null);
        dao.addTransactionToAccount(t, id);
        System.out.println("Transaction added to account.");
    }
}
