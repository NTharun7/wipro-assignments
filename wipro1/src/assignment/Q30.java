package assignment;
import java.util.Scanner;

public class Q30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        sc.nextLine(); // This line is important to consume the leftover newline

        String[] arr1 = new String[n];
        String[] arr2 = new String[n];

        System.out.println("Enter first array:");
        String[] input1 = sc.nextLine().split(" ");
        System.arraycopy(input1, 0, arr1, 0, n);

        System.out.println("Enter second array:");
        String[] input2 = sc.nextLine().split(" ");
        System.arraycopy(input2, 0, arr2, 0, n);

        boolean isSame = true;
        for (int i = 0; i < n; i++) {
            if (!arr1[i].equalsIgnoreCase(arr2[i])) {
                isSame = false;
                break;
            }
        }

        System.out.println(isSame);
        sc.close();
    }
}

/*Enter number of elements:
3
Enter first array:
Java Python C
Enter second array:
java PYTHON c
true
 */
