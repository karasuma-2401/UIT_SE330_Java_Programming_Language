import java.io.*;

public class CreateData {
    public static void main(String[] args) {
        int[] dataA = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        
        try (PrintWriter pw = new PrintWriter(new FileWriter("a.txt"))) {
            for (int i : dataA) pw.print(i + " ");
            System.out.println("Thành công: Đã tạo file văn bản a.txt");
        } catch (IOException e) { e.printStackTrace(); }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("a.dat"))) {
            for (int i : dataA) dos.writeInt(i);
            System.out.println("Thành công: Đã tạo file nhị phân a.dat");
        } catch (IOException e) { e.printStackTrace(); }
    }
}