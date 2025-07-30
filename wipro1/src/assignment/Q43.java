package assignment;

import java.io.*;

public class Q43 {
    public static void main(String[] args) {
        // Updated file path to match location inside 'src/assignment'
        String filePath = "src/assignment/sample.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Reading file:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

/*
 Reading file:
Reading file:
Hello, this is a test file.
Line 2 of the file.

*/