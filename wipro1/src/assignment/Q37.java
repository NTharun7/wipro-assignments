package assignment;

import java.io.*;
import java.util.Scanner;

public class Q37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "batchmates.txt";

        try {
            // Writing names to the file
            FileWriter writer = new FileWriter(filename);
            System.out.print("Enter number of batchmates: ");
            int count = Integer.parseInt(scanner.nextLine());

            for (int i = 1; i <= count; i++) {
                System.out.print("Enter name of batchmate " + i + ": ");
                String name = scanner.nextLine();
                writer.write(name + "\n");
            }
            writer.close();
            System.out.println("✅ Names saved to " + filename);

            // Reading and displaying from the file
            System.out.println("\n📄 Names of your batchmates from file:");
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("" + line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("❌ File Error: " + e.getMessage());
            scanner.close();
        }
    }
}

/*Enter number of batchmates: 3
Enter name of batchmate 1: rahul
Enter name of batchmate 2: priya
Enter name of batchmate 3: vinay
✅ Names saved to batchmates.txt

📄 Names of your batchmates from file:
 rahul
 priya
 vinay
*/

