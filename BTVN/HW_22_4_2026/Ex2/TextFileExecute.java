import java.io.*;
import java.util.*;

public class TextFileExecute {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("a.txt"));
            List<Integer> A = new ArrayList<>();
            while (sc.hasNextInt()) A.add(sc.nextInt());
            sc.close();

            int m = 10;
            int[] B = new int[m];
            PrintWriter pwB = new PrintWriter("b.txt");
            for (int i = 0; i < m; i++) {
                B[i] = (int) (Math.random() * 100);
                pwB.print(B[i] + " ");
            }
            pwB.close();

            List<Integer> C = new ArrayList<>(A);
            if (C.size() >= 13) {
                C.remove(12);
                C.add(12, B[m-1]); 
                C.add(12, B[m-2]); 
                C.add(12, B[m-3]);
            }

            Collections.sort(C);
            PrintWriter pwC = new PrintWriter("c.txt");
            for (int val : C) pwC.print(val + " ");
            pwC.close();

            System.out.println("Xử lý Text File hoàn tất.");
        } catch (IOException e) { e.printStackTrace(); }
    }
}