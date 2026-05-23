package Ex13;

import java.util.Scanner;

    public class ExecTimer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N: ");
        long n = sc.nextLong();

        long start = System.nanoTime();

        long sum = 0;
        for (long i = 1; i <= n; i++) {
            sum += i;
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1000000.0;

        System.out.println("=> Sum: " + sum);
        System.out.println("=> Execution time: " + timeMs + " ms");

        sc.close();
    }
}
