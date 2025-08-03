package mymainfiles;

import java.util.List;
import java.util.Scanner;

import mydaofiles.ItemDAO;
import mypojos.Item;

public class ItemCRUDMain {
    static Scanner sc = new Scanner(System.in);
    static ItemDAO dao = new ItemDAO();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Add Item");
            System.out.println("2. View All");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Get by ID");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addItem();
                case 2 -> viewAll();
                case 3 -> updateItem();
                case 4 -> deleteItem();
                case 5 -> getById();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addItem() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        Item item = new Item(name, price);
        dao.addItem(item);
        System.out.println("Item added.");
    }

    private static void viewAll() {
        List<Item> items = dao.getAllItems();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void updateItem() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Item item = dao.getItemById(id);
        if (item != null) {
            System.out.print("New name: ");
            item.setName(sc.nextLine());
            System.out.print("New price: ");
            item.setPrice(sc.nextDouble());
            dao.updateItem(item);
            System.out.println("Item updated.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void deleteItem() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        Item item = dao.getItemById(id);
        if (item != null) {
            dao.deleteItem(item);
            System.out.println("Item deleted.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void getById() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        Item item = dao.getItemById(id);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println("Item not found.");
        }
    }
}
