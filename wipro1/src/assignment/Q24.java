package assignment;

import java.util.Scanner;

public class Q24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] speeds = new int[5];
        int sum = 0;

        System.out.println("Enter speeds of 5 bikers:");
        for (int i = 0; i < 5; i++) {
            speeds[i] = sc.nextInt();
            sum += speeds[i];
        }

        double avg = sum / 5.0;
        System.out.println("Average speed: " + avg);
        System.out.println("Qualifying racers:");

        for (int speed : speeds) {
            if (speed > avg) {
                System.out.println(speed);
           sc.close(); }
        }
    }
}



/*Enter speeds of 5 bikers:
30
50
70
75
80
Average speed: 61.0
Qualifying racers:
70
75
80
*/
