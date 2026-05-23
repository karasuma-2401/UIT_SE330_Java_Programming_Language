import java.io.*;
import java.util.*;

public class MatrixBinaryExecute {
    public static void main(String[] args) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("matrix.bin"));
            int m = dis.readInt();
            int n = dis.readInt();
            int[][] matrix = new int[m][n];

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            long sum = 0;
            List<Integer> primes = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = dis.readInt();
                    int val = matrix[i][j];

                    if (val < min) min = val;
                    if (val > max) max = val;
                    sum += val;
                    if (isPrime(val)) primes.add(val);
                }
            }
            dis.close();

            DataOutputStream dos = new DataOutputStream(new FileOutputStream("output_binary.bin"));
            dos.writeInt(min);
            dos.writeInt(max);
            dos.writeLong(sum);
            dos.writeInt(primes.size());
            for (int prime : primes) {
                dos.writeInt(prime);
            }
            dos.close();

            System.out.println("Binary matrix processing completed.");
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