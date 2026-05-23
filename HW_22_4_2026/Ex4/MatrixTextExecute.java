import java.io.*;
import java.util.*;
public class MatrixTextExecute {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("matrix.txt"));
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] matrix = new int[m][n];

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            long sum = 0;
            List<Integer> primes = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = sc.nextInt();
                    int val = matrix[i][j];

                    if (val < min) min = val;
                    if (val > max) max = val;
                    sum += val;
                    if (isPrime(val)) primes.add(val);
                }
            }
            sc.close();

            PrintWriter pw = new PrintWriter("output_text.txt");
            pw.println("Minimum value: " + min);
            pw.println("Maximum value: " + max);
            pw.println("Sum of all values: " + sum);
            pw.println("Prime numbers: " + primes);
            pw.close();

            System.out.println("Text matrix processing completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
