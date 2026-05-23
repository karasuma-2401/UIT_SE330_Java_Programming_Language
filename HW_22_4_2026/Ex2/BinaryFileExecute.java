import java.io.*;
import java.util.*;

public class BinaryFileExecute {
    public static void main(String[] args) {
        try {
            List<Integer> A = new ArrayList<>();
            try (DataInputStream dis = new DataInputStream(new FileInputStream("a.dat"))) {
                while (dis.available() >= 4) {
                    A.add(dis.readInt());
                }
            }

            int m = 10;
            int[] B = new int[m];
            try (DataOutputStream dosB = new DataOutputStream(new FileOutputStream("b.dat"))) {
                for (int i = 0; i < m; i++) {
                    B[i] = (int) (Math.random() * 100);
                    dosB.writeInt(B[i]);
                }
            }

            List<Integer> C = new ArrayList<>(A);
            if (C.size() >= 13) {
                C.remove(12);
                C.add(12, B[m-1]); 
                C.add(12, B[m-2]); 
                C.add(12, B[m-3]);
            }

            Collections.sort(C);
            try (DataOutputStream dosC = new DataOutputStream(new FileOutputStream("c.dat"))) {
                for (int val : C) dosC.writeInt(val);
            }

            System.out.println("Xử lý Binary File hoàn tất. Kết quả: " + C);
        } catch (IOException e) { e.printStackTrace(); }
    }
}