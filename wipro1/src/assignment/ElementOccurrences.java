package assignment;

public class ElementOccurrences {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 3, 2, 6, 5, 2, 3, 6}; // 10 elements

        boolean[] visited = new boolean[arr.length]; // Track already counted elements

        System.out.println("Element : Frequency");

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue; // Skip if already counted
            }

            int count = 1; // Count current element
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                    visited[j] = true; // Mark as counted
                }
            }

            System.out.println(arr[i] + " : " + count);
        }
    }
}

