package Ex6;

import java.util.Random;
import java.util.Scanner;

public class RandomWalk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the number of steps for the random walk: ");
        int steps = sc.nextInt();
        int x = 0;
        int y = 0;

        for (int i = 0; i < steps; i++) {
            int direction = rand.nextInt(4);

            switch (direction) {
                case 0:
                    y++; 
                    break;
                case 1:
                    y--; 
                    break;
                case 2:
                    x--; 
                    break;
                case 3:
                    x++; 
                    break;
            }
        }
        double distance = Math.sqrt(x * x + y * y);

        System.out.println("Starting position: (0, 0)");
        System.out.println("Total steps taken: " + steps);
        System.out.println("Final position: (" + x + ", " + y + ")");
        System.out.printf("Euclidean distance: %.4f\n", distance);

        sc.close();
    }
}